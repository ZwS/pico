package com.asudak.pico.db.repository.support;

import com.asudak.pico.db.repository.DbRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class JpaRepository<T, ID> implements DbRepository<T, ID> {

    protected Class<T> entityClass;
    @Inject
    protected EntityManager em;

    public JpaRepository() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[1];
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(entityClass);
        Root<T> entity = query.from(entityClass);
        query.select(entity);

        return em.createQuery(query).getResultList();
    }

    @Override
    public Optional<T> findOne(ID id) {
        return Optional.ofNullable(em.find(entityClass, id));
    }

    public T save(T entity) {
        return Optional.of(entity).filter(e -> em.contains(e))
                .orElseGet(() -> em.merge(entity));
    }

    public void delete(T entity) {
        em.remove(entity);
    }
}
