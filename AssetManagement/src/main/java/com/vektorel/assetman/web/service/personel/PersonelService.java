package com.vektorel.assetman.web.service.personel;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vektorel.assetman.web.entity.Kisi;
import com.vektorel.assetman.web.entity.Personel;
import com.vektorel.assetman.web.service.BaseDao;
import com.vektorel.assetman.web.utilities.IDataService;
import com.vektorel.assetman.web.utilities.PagingResult;
import com.vektorel.assetman.web.utilities.ex.DbException;

@Service("personelService")
public class PersonelService implements IDataService<Personel> {

	
	@Autowired
	private transient BaseDao baseDao;
	public PersonelService() {
		super();
		System.out.println("Personel Service Oluþturuldu.");
	}

	@Override
	public Personel save(Personel entity) throws DbException {
		return (Personel)baseDao.save(entity);
	}

	@Override
	public Personel update(Personel entity) throws DbException {
		return (Personel)baseDao.update(entity);
	}

	@Override
	public boolean delete(Personel entity) throws DbException {
		return baseDao.delete(entity);
	}

	@Override
	public List<Personel> getAll() {
		return (List<Personel>) baseDao.getAll(Personel.class);
	}

	@Override
	public Personel getById(Long entityId) {
		return (Personel) baseDao.getById(entityId, Personel.class);
	}

	public void delete(Long id)throws DbException {
		delete(getById(id));		
	}
	
	@Transactional
	public PagingResult getAllByPaging(int first, int pageSize, SortOrder sortOrder, Map<String, Object> filters) {		
		Session session = baseDao.getSession();
		Criteria criteria = session.createCriteria(Personel.class);
		
		
		
		int totalResult = Integer.parseInt(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
		
		criteria.setProjection(null);	
		criteria.setMaxResults(pageSize);
		criteria.setFirstResult(first);
		criteria.addOrder(Order.desc("id"));
		return new PagingResult(criteria.list(), totalResult);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Personel> acompKisi(String term) {
		Session session = baseDao.getSession();
		Criteria criteria = session.createCriteria(Personel.class);		
		criteria.add(Restrictions.or(Restrictions.ilike("ad",term,MatchMode.ANYWHERE),
									 Restrictions.ilike("soyad",term,MatchMode.ANYWHERE) ));		
		criteria.addOrder(Order.asc("ad"));
		return criteria.list();
	}
}
