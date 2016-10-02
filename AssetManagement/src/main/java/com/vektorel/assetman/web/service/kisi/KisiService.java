package com.vektorel.assetman.web.service.kisi;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.primefaces.model.SortOrder;

import com.vektorel.assetman.web.entity.Kisi;
import com.vektorel.assetman.web.service.BaseDao;
import com.vektorel.assetman.web.utilities.IDataService;
import com.vektorel.assetman.web.utilities.PagingResult;

public class KisiService implements IDataService<Kisi>{

	BaseDao baseDao=new BaseDao();
	
	
	@Override
	public Kisi save(Kisi entity) {
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

	public PagingResult getAllByPaging(int first, int pageSize, SortOrder sortOrder, Map<String, Object> filters) {		
		Session session = baseDao.getSession();
		Criteria criteria = session.createCriteria(Kisi.class);
		
		int totalResult = Integer.parseInt(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
		
		criteria.setProjection(null);	
		criteria.setMaxResults(pageSize);
		criteria.setFirstResult(first);
		criteria.addOrder(Order.desc("id"));
		return new PagingResult(criteria.list(), totalResult);
	}


}
