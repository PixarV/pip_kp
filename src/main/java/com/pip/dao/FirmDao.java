package dao;

import com.pip.entities.Firm;
import org.springframework.stereotype.Repository;

@Repository
public class FirmDao extends CommonDao<Firm> {
    public FirmDao() {
        super(Firm.class);
    }
}

