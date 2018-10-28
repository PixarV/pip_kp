package dao;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class CommonDao<T> {

    final Class<T> type;

    @PersistenceContext(unitName = "pip")
    EntityManager entityManager;

    /**
     * Use save if you 100% sure that this entity is not in db.
     * @param entity - new entity WITHOUT id
     */
    public void saveEntity(T entity) {
        entityManager.persist(entity);
    }

    public T getEntity(int id) {
        return entityManager.find(type, id);
    }

    /**
     * Use update if you want to avoid problems with detached entities. But your new instance saved without set id.
     * @param entity new Entity with OR without id
     * @return merged Entity
     */
    public T updateEntity(T entity) {
        return entityManager.merge(entity);
    }

    public void updateFromDB(T entity) {
        if (!entityManager.contains(entity))
            entity = entityManager.merge(entity);
        entityManager.refresh(entity);
    }
}
