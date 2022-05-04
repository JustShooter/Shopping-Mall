package by.it.academy.justshooter.dao;

import by.it.academy.justshooter.dao.interfaces.ItemDao;
import by.it.academy.justshooter.dao.parentdao.Dao;
import by.it.academy.justshooter.entity.Item;

public class ItemDaoImpl extends Dao<Item> implements ItemDao {

    public ItemDaoImpl() {
        super(Item.class);
    }

}
