package com.pip.dao;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class CommonDao<T> {

    final Class<T> type;

    @Autowired
    EntityManager entityManager;

    /**
     * Use save if you 100% sure that this entity is not in db.
     * @param entity - new entity WITHOUT id
     */
    public void saveEntity(T entity) {
        entityManager.persist(entity);
    }

    public T findEntityById(int id) {
        return entityManager.find(type, id);
    }

    public void detachEntity(T entity) {
        entityManager.detach(entity);
    }

    /**
     * Use update if you want to avoid problems with detached entities. But your new instance saved without set id.
     * @param entity new Entity with OR without id
     * @return merged Entity
     */
    public T mergeEntity(T entity) {
        return entityManager.merge(entity);
    }

    public void refreshEntity(T entity) {
        entityManager.refresh(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    public List<T> findAllEntities() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<T> query = criteriaBuilder.createQuery(type);
        Root<T> all = query.from(type);
        query.select(all);
        return entityManager.createQuery(query).getResultList();
    }

    public void removeEntity(T entity) {
       entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }
}
