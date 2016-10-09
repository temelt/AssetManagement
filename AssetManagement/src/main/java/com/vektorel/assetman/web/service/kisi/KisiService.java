package com.vektorel.assetman.web.service.kisi;

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
import com.vektorel.assetman.web.service.BaseDao;
import com.vektorel.assetman.web.utilities.IDataService;
import com.vektorel.assetman.web.utilities.PagingResult;
import com.vektorel.assetman.web.utilities.ex.DbException;

@Service("kisiService")
public class KisiService implements IDataService<Kisi>{

	
	@Autowired
	private transient BaseDao baseDao;
	public KisiService() {
		super();
		System.out.println("KisiService Oluþturuldu.");
	}
	
	@Override
	public Kisi save(Kisi entity) throws DbException {
		if(entity.getAd()==null || entity.getAd().trim().equals(""))
			throw new DbException("Ad Boþ Olamaz");
		
		return (Kisi)baseDao.save(entity);
		
	}

	@Override
	public Kisi update(Kisi entity) {
		return (Kisi)baseDao.update(entity);
	}

	@Override
	public boolean delete(Kisi entity) {
		return baseDao.delete(entity);
	}

	@Override
	public List<Kisi> getAll() {
		return (List<Kisi>) baseDao.getAll(Kisi.class);
	}

	@Override
	public Kisi getById(Long entityId) {
		return (Kisi) baseDao.getById(entityId, Kisi.class);
	}

	public void delete(Long id) {
		delete(getById(id));		
	}

	@Transactional
	public PagingResult getAllByPaging(int first, int pageSize, SortOrder sortOrder, Map<String, Object> filters) {		
		Session session = baseDao.getSession();
		Criteria criteria = session.createCriteria(Kisi.class);
		
		if(filters.get("ad")!=null){
			criteria.add(Restrictions.ilike("ad",filters.get("ad").toString(),  MatchMode.ANYWHERE));
		}
		
		int totalResult = Integer.parseInt(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
		
		criteria.setProjection(null);	
		criteria.setMaxResults(pageSize);
		criteria.setFirstResult(first);
		criteria.addOrder(Order.desc("id"));
		return new PagingResult(criteria.list(), totalResult);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Kisi> acompKisi(String term) {
		Session session = baseDao.getSession();
		Criteria criteria = session.createCriteria(Kisi.class);		
		criteria.add(Restrictions.or(Restrictions.ilike("ad",term,MatchMode.ANYWHERE),
									 Restrictions.ilike("soyad",term,MatchMode.ANYWHERE) ));		
		criteria.addOrder(Order.asc("ad"));
		return criteria.list();
	}


}
