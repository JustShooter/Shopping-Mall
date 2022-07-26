package by.it.academy.justshooter.dao.interfaces;

import by.it.academy.justshooter.dao.exception.NoDataFoundById;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface DaoInterface<T> extends Serializable {
    T findOne(Object id) throws NoDataFoundById;

    List<T> findAll();

    T create(T entity);

    T update(T incomingEntity);

    void delete(T entity);

    void deleteById(Integer entityId) throws NoDataFoundById;

    void closeAll();
}
