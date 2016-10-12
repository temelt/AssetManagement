package com.vektorel.assetman.web.service.proje;
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
import com.vektorel.assetman.web.entity.Proje;
import com.vektorel.assetman.web.service.BaseDao;
import com.vektorel.assetman.web.utilities.IDataService;
import com.vektorel.assetman.web.utilities.PagingResult;
import com.vektorel.assetman.web.utilities.ex.DbException;

@Service("projeService")

public class ProjeService implements IDataService<Proje>{


	@Autowired
	private transient BaseDao baseDao;
	public ProjeService() {
		super();
		System.out.println("ProjeService Oluþturuldu.");
	}
	@Override
	public Proje save(Proje entity) throws DbException {
		return (Proje)baseDao.save(entity);
	}
	@Override
	public Proje update(Proje entity) throws DbException {
		return (Proje)baseDao.update(entity);

	}
	@Override
	public boolean delete(Proje entity) throws DbException {
		return baseDao.delete(entity);
	}
	@Override
	public List<Proje> getAll() {
		return (List<Proje>) baseDao.getAll(Proje.class);
	}
	@Override
	public Proje getById(Long entityId) {
		return (Proje) baseDao.getById(entityId, Proje.class);
	}
	
	public void delete(Long id) {
		try {
			delete(getById(id));
		} catch (DbException e) {
			e.printStackTrace();
		}		
	}
	
	@Transactional
	public PagingResult getAllByPaging(int first, int pageSize, SortOrder sortOrder, Map<String, Object> filters) {		
		Session session = baseDao.getSession();
		Criteria criteria = session.createCriteria(Proje.class);
		
		if(filters.get("adi")!=null){
			criteria.add(Restrictions.ilike("adi",filters.get("adi").toString(),  MatchMode.ANYWHERE));
		}
		
		if(filters.get("kod")!=null){
			criteria.add(Restrictions.ilike("kod",filters.get("kod").toString(),  MatchMode.ANYWHERE));
		} 
		
		int totalResult = Integer.parseInt(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
		
		criteria.setProjection(null);	
		criteria.setMaxResults(pageSize);
		criteria.setFirstResult(first);
		criteria.addOrder(Order.desc("id"));
		return new PagingResult(criteria.list(), totalResult);
	}
	}
	
