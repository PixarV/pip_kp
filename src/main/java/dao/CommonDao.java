package dao;

import lombok.RequiredArgsConstructor;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@RequiredArgsConstructor
public abstract class CommonDao<T> {

    final Class<T> type;

    @PersistenceContext(unitName = "pip")
    private EntityManager entityManager;

    // TODO: 10/22/18 Transactions
    public void saveEntity(T entity) {
        entityManager.persist(entity);
    }

    public T getEntity(int id) {
        return entityManager.find(type, id);
    }

    public T updateEntity(T entity) {
        return entityManager.merge(entity);
    }
}
