package com.asudak.pico.db.repository.support;

import com.asudak.pico.db.model.page.Page;
import com.asudak.pico.db.model.page.PageRequest;
import com.asudak.pico.db.repository.PagingRepository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

public abstract class PagingJpaRepository<T, ID> extends JpaRepository<T, ID> implements PagingRepository<T, ID> {

    @Override
    public Page<T> findPage(PageRequest request) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(entityClass);
        Root<T> entity = query.from(entityClass);
        query.select(entity);

        List<T> entities = em.createQuery(query)
                .setFirstResult(request.getOffset())
                .setMaxResults(request.getPageSize())
                .getResultStream()
                .collect(Collectors.toList());

        return Optional.of(entities)
                .filter(not(List::isEmpty))
                .map(content -> Page.of(content, countPages(request), hasNext(request), request))
                .orElseGet(() -> Page.empty(request));
    }

    private boolean hasNext(PageRequest request) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Integer> query = cb.createQuery(Integer.class);

        query.from(entityClass);
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
        Root<T> root = query.from(entityClass);
        query.select(cb.count(root));

        double pages = (double) em.createQuery(query).getSingleResult() / request.getPageSize();
        return (int) Math.ceil(pages);
    }
}
