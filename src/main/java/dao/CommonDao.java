package dao;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class CommonDao<T> {

    final Class<T> type;

    @PersistenceContext(unitName = "pip")
    EntityManager entityManager;

    // TODO: 10/22/18 Transactions

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
        entityManager.refresh(entity);
    }
}
