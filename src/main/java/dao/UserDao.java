package dao;

import entity.UserEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserDao {

    @PersistenceContext(unitName = "pip")
    private EntityManager entityManager;

    public void saveUser(UserEntity user) {
        entityManager.persist(user);

    }
}
