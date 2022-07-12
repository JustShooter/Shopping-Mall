package by.it.academy.justshooter.dao;

import by.it.academy.justshooter.dao.interfaces.AddressDao;
import by.it.academy.justshooter.dao.abstractdao.Dao;
import by.it.academy.justshooter.entity.Address;

public class AddressDaoImpl extends Dao<Address> implements AddressDao {

    public AddressDaoImpl() {
        super(Address.class);
    }

}
