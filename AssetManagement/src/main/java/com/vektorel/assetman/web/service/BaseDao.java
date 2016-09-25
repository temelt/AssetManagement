package com.vektorel.assetman.web.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.vektorel.assetman.web.utilities.HibernateUtil;

public class BaseDao {
	public Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    public Object save(Object entity) {
        Session s = getSession();
        Transaction trans = s.beginTransaction();
        s.save(entity);
        trans.commit();
        return entity;
    }

    public Object update(Object entity) {
        Session s = getSession();
        Transaction trans = s.beginTransaction();
        s.update(entity);
        trans.commit();
        return entity;
    }

    public boolean delete(Object entity) {
        Session s = getSession();
        Transaction trans = s.beginTransaction();
        s.delete(entity);
        trans.commit();
        return true;
    }

    public List getAll(Class cls) {
        Session s = getSession();
        Criteria cr = s.createCriteria(cls);
        return cr.list();
    }

    public Object getById(Long entityId,Class cls) {
        Session s = getSession();
        Criteria cr = s.createCriteria(cls);
        cr.add(Restrictions.eq("id", entityId));
        return cr.uniqueResult();
    }
}
