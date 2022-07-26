package by.it.academy.justshooter.dao.interfaces;

import by.it.academy.justshooter.entity.Shop;

import java.util.List;

public interface ShopDao extends DaoInterface<Shop> {

    List<Shop> getShopsByCategoryId(Integer id);

}
