package com.vektorel.assetman.web.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("baseDao")
public class BaseDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public BaseDao() {
		super();
		System.out.println("BaseDao Oluþturuldu.");
	}
	
	@Transactional(readOnly=false)
    public Object save(Object entity) {
		getSession().save(entity);
        return entity;
    }

    @Transactional(readOnly=false)
    public Object update(Object entity) {
    	getSession().update(entity);
        return entity;
    }

    @Transactional(readOnly=false)
    public boolean delete(Object entity) {
    	getSession().delete(entity);
        return true;
    }

    @Transactional
    public List getAll(Class cls) {
        Criteria cr = getSession().createCriteria(cls);
        return cr.list();
    }

    @Transactional
    public Object getById(Long entityId,Class cls) {
        Criteria cr = getSession().createCriteria(cls);
        cr.add(Restrictions.eq("id", entityId));
        return cr.uniqueResult();
    }
    
    public Session getSession() {
    	return sessionFactory.getCurrentSession();
	}
}
