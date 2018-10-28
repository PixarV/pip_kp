package dao;

import entities.Relation;

import javax.ejb.Stateless;

@Stateless
public class RelationDao extends CommonDao<Relation> {
    public RelationDao() {
        super(Relation.class);
    }
}
