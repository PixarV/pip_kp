package dao;

import com.pip.entities.Weapon;
import org.springframework.stereotype.Repository;

@Repository
public class WeaponDao extends CommonDao<Weapon> {
    public WeaponDao() {
        super(Weapon.class);
    }
}

