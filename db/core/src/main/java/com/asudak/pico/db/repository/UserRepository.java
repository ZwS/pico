package com.asudak.pico.db.repository;

import com.asudak.pico.db.entity.User;
import com.asudak.pico.db.entity.User_;
import com.asudak.pico.db.repository.support.PagingJpaRepository;

import javax.ejb.Singleton;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Singleton
public class UserRepository extends PagingJpaRepository<User, UUID> {

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
}
