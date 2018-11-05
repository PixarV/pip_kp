package dao;

import com.pip.entities.Relation;
import org.springframework.stereotype.Repository;

@Repository
public class RelationDao extends CommonDao<Relation> {
    public RelationDao() {
        super(Relation.class);
    }
}
