package by.it.academy.justshooter.dao;

import by.it.academy.justshooter.dao.interfaces.CategoryDao;
import by.it.academy.justshooter.dao.abstractdao.Dao;
import by.it.academy.justshooter.entity.Category;

public class CategoryDaoImpl extends Dao<Category> implements CategoryDao {
    public CategoryDaoImpl() {
        super(Category.class);
    }
}
