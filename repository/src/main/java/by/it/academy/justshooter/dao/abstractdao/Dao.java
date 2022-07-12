package by.it.academy.justshooter.dao.abstractdao;

import by.it.academy.justshooter.dao.exception.NoDataFoundById;
import by.it.academy.justshooter.dao.interfaces.DaoInterface;
import by.it.academy.justshooter.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public abstract class Dao<T> implements DaoInterface<T> {
    private final Class<T> entityClass;
    protected final EntityManager entityManager;


    protected Dao(Class<T> incomingClass) {
        this.entityClass = incomingClass;
        entityManager = HibernateUtil.getEntityManager();
    }


    @Override
    public T findOne(final Object id) throws NoDataFoundById {
        return Optional
                .ofNullable(entityManager.find(entityClass, id))
                .orElseThrow(() -> new NoDataFoundById("No such id found!"));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return (List<T>) entityManager.createQuery("from " + entityClass.getName()).getResultList();
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
    public void deleteById(final Integer entityId) throws NoDataFoundById {
        entityManager.getTransaction().begin();
        final T entity = Optional
                .ofNullable(entityManager.find(entityClass, entityId))
                .orElseThrow(() -> new NoDataFoundById("No such id found!"));
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void closeAll(){
        entityManager.close();
        HibernateUtil.close();
    }
}