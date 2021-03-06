package br.ufba.reuse.bolao.business.daos.util;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class DAOGeneric<T, ID extends Serializable> {

    @Autowired
    private SessionFactory sessionFactory;

    // injected from Spring
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public org.hibernate.Session session() {
        return sessionFactory.getCurrentSession();
    }

    public DAOGeneric() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private Class<T> persistentClass;

    public Class<T> clazz() {
        return this.persistentClass;
    }

    public void delete(T entity) {
        session().delete(entity);
    }
    
    public void delete(ID id) {
        session().delete(id);
    }

    public T findById(ID id) {
        return (T) session().get(clazz(), id);
    }

    public List<T> listAll() {
        Criteria crit = session().createCriteria(clazz());
//        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return crit.list();
    }

    public List<T> listAllHQL() {
        return findByHQL("FROM " + clazz().getSimpleName());
    }

    public long sumColunm(String coluna){
        Criteria criteria = session().createCriteria(clazz())   
        .setProjection(Projections.sum(coluna));
        Long valorTotal = (Long) criteria.uniqueResult();
        if(valorTotal == null) valorTotal = 0l;
        return valorTotal;
    }

    public int size() {
        Criteria crit = session().createCriteria(clazz());
        crit.setProjection(Projections.rowCount());
        Object result = crit.uniqueResult();
        Long size = (Long) result;
        return size.intValue();
    }

    public T save(T entity) {
        session().saveOrUpdate(entity);
        return entity;
    }

    public List<T> findByCriteriaReturnList(Criterion... criterion) {
        return findByCriteria(null, criterion);
    }

    public T findByCriteriaReturnUniqueResult(Criterion... criterion) {
        return findByCriteriaReturnUniqueResult(null, criterion);
    }

    public List<T> findByCriteria(Order order, Criterion... criterion) {
        Criteria crit = session().createCriteria(clazz());
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        for (Criterion c : criterion) {
            crit.add(c);
        }
        if (order != null) {
            crit.addOrder(order);
        }
        return crit.list();
    }

    public List<T> findByCriteria2(List<Order> orders, List<Criterion> criterion, Map<String,String> alias) {
        Criteria crit = session().createCriteria(clazz());

        if(alias != null) {
            for (Map.Entry<String, String> entry : alias.entrySet()) {
                crit.createAlias(entry.getKey(), entry.getValue());
            }
        }

        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        for (Criterion c : criterion) {
            crit.add(c);
        }
        for (Order o : orders) {
            crit.addOrder(o);
        }
        return crit.list();
    }

    public T findByCriteriaReturnUniqueResult(Order order, Criterion... criterion) {
        Criteria crit = session().createCriteria(clazz());
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        for (Criterion c : criterion) {
            crit.add(c);
        }
        if (order != null) {
            crit.addOrder(order);
        }
        return (T) crit.uniqueResult();
    }

    public T findByCriteriaReturnUniqueResult(Order order, int offSet, int size, Criterion... criterion) {
        Criteria crit = session().createCriteria(clazz());
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        crit.setFirstResult(offSet);
        crit.setMaxResults(size);
        for (Criterion c : criterion) {
            crit.add(c);
        }
        if (order != null) {
            crit.addOrder(order);
        }
        return (T) crit.uniqueResult();
    }

    public List<T> findByCriteria(Order order) {
        Criteria crit = session().createCriteria(clazz());
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if (order != null) {
            crit.addOrder(order);
        }
        return crit.list();
    }

    public List<T> findByCriteria(Order order, int offSet, int size) {
        Criteria crit = session().createCriteria(clazz());
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        crit.setFirstResult(offSet);
        crit.setMaxResults(size);
        if (order != null) {
            crit.addOrder(order);
        }
        return crit.list();
    }

    public List<T> findByHQL(Order order, int offSet, int size) {
        Query query = session().createQuery("FROM " + clazz().getSimpleName() + " order by " + order.getPropertyName() );
        query.setFirstResult(offSet);
        query.setMaxResults(size);
        return query.list();
    }

    public List<T> findByHQL(String hql){
        Query query = session().createQuery(hql);
        return query.list();
    }

    public void executeSQL(String query) {
        session().createSQLQuery(query).executeUpdate();
    }

    public List executeSQL_List(String sql) {
        SQLQuery q = session().createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        return q.list();
    }
}
