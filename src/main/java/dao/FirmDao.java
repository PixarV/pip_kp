package dao;

import entities.Firm;

import javax.ejb.Stateless;

@Stateless
public class FirmDao extends CommonDao<Firm> {
    public FirmDao() {
        super(Firm.class);
    }
}

