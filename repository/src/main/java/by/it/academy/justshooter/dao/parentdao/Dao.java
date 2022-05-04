package by.it.academy.justshooter.dao.parentdao;

import by.it.academy.justshooter.dao.interfaces.DaoInterface;
import by.it.academy.justshooter.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class Dao<T> implements DaoInterface<T> {
    private final Class<T> entityClass;
    private final EntityManager entityManager;


    public Dao(Class<T> incomingClass) {
        this.entityClass = incomingClass;
        this.entityManager = HibernateUtil.getEntityManager();
    }


    @Override
    public T findOne(final Object id) throws SQLException {
        T entity = entityManager.find(entityClass, id);
        if(entity==null){
            throw new SQLException("No such id found!");
        }
        return entity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return entityManager.createQuery("from " + entityClass.getName()).getResultList();
    }

    @Override
    public T create(final T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public T update(final T incomingEntity) {
        entityManager.getTransaction().begin();
        T entity = entityManager.merge(incomingEntity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public void delete(final T entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteById(final Integer entityId) throws SQLException {
        final T entity = findOne(entityId);
        delete(entity);
    }

    @Override
    public void closeAll(){
        entityManager.close();
        HibernateUtil.close();
    }
}