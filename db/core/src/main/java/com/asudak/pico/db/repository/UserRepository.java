package com.asudak.pico.db.repository;

import com.asudak.pico.db.entity.User;
import com.asudak.pico.db.entity.User_;
import com.asudak.pico.db.model.page.Page;
import com.asudak.pico.db.model.page.PageRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

@ApplicationScoped
public class UserRepository {

    @Inject
    EntityManager em;

    public Page<User> findUsers(@NotNull PageRequest request) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> user = query.from(User.class);
        query.select(user);

        List<User> users = em.createQuery(query)
                .setFirstResult(request.getOffset())
                .setMaxResults(request.getPageSize())
                .getResultStream()
                .collect(Collectors.toList());

        return Optional.of(users)
                .filter(not(List::isEmpty))
                .map(content -> Page.of(content, countPages(request), hasNext(request), request))
                .orElseGet(() -> Page.empty(request));
    }

    public Optional<User> findUserById(@NotNull UUID id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    public Optional<User> findUserByUsername(String username) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> user = query.from(User.class);

        query
                .select(user)
                .where(cb.equal(user.get(User_.USERNAME), username));

        try {
            return Optional.of(em.createQuery(query).getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public UUID save(@NotNull User user) {
        return Optional.ofNullable(user.getId()).map(id -> em.find(User.class, id))
                .map(em::merge)
                .orElseGet(() -> {
                    em.persist(user);
                    return user;
                }).getId();
    }

    public boolean userExists(@NotNull String username) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Integer> query = cb.createQuery(Integer.class);
        Root<User> user = query.from(User.class);

        query
                .select(cb.literal(1))
                .where(cb.equal(user.get(User_.USERNAME), username));

        try {
            em.createQuery(query).getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    private boolean hasNext(PageRequest request) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Integer> query = cb.createQuery(Integer.class);

        query.from(User.class);
        query.select(cb.literal(1));

        try {
            em.createQuery(query)
                    .setFirstResult(request.getOffset() + request.getPageSize() + 1)
                    .getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    private int countPages(PageRequest request) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<User> root = query.from(User.class);
        query.select(cb.count(root));

        double pages = (double) em.createQuery(query).getSingleResult() / request.getPageSize();
        return (int) Math.ceil(pages);
    }
}
