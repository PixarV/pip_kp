package dao;

import com.pip.entities.Tank;
import org.springframework.stereotype.Repository;

@Repository
public class TankDao extends CommonDao<Tank> {
    public TankDao() {
        super(Tank.class);
    }
}

