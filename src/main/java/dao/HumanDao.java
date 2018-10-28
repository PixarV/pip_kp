package dao;

import entities.Human;

import javax.ejb.Stateless;

@Stateless
public class HumanDao extends CommonDao<Human> {
    public HumanDao() {
        super(Human.class);
    }
}
