package com.asudak.pico.db.repository;

import com.asudak.pico.db.entity.AccessToken;
import com.asudak.pico.db.entity.RefreshToken;
import com.asudak.pico.db.entity.Token;
import com.asudak.pico.db.entity.Token_;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotEmpty;
import java.util.Optional;

public class TokenRepository {


    private final EntityManager em;

    @Inject
    public TokenRepository(EntityManager em) {
        this.em = em;
    }

    public Optional<AccessToken> findAccessToken(@NotEmpty String token) {
        return findTokenByUserAndToken(AccessToken.class, token);
    }

    public Optional<RefreshToken> findRefreshToken(@NotEmpty String token) {
        return findTokenByUserAndToken(RefreshToken.class, token);
    }

    private <T extends Token> Optional<T> findTokenByUserAndToken(Class<T> clazz, String token) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(clazz);
        Root<T> tokenRoot = query.from(clazz);

        query
                .select(tokenRoot)
                .where(cb.equal(tokenRoot.get(Token_.token), token));

        try {
            return Optional.of(em.createQuery(query).getSingleResult());
        } catch (EntityNotFoundException e) {
            return Optional.empty();
        }
    }

    public <T extends Token> T save(T token) {
        return Optional.of(token)
                .filter(em::contains)
                .map(em::merge)
                .orElseGet(() -> {
                    em.persist(token);
                    return token;
                });
    }
}
