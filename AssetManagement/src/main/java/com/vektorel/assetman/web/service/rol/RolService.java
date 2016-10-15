package com.vektorel.assetman.web.service.rol;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vektorel.assetman.web.entity.Rol;
import com.vektorel.assetman.web.service.BaseDao;
import com.vektorel.assetman.web.utilities.IDataService;
import com.vektorel.assetman.web.utilities.PagingResult;
import com.vektorel.assetman.web.utilities.ex.DbException;

@Service("rolService")
public class RolService implements IDataService<Rol> {
	
	@Autowired
	private transient BaseDao baseDao;

	@Override
	public Rol save(Rol entity) throws DbException {
		return (Rol) baseDao.save(entity);
	}

	@Override
	public Rol update(Rol entity) throws DbException {
		return (Rol) baseDao.update(entity);
	}

	@Override
	public boolean delete(Rol entity) throws DbException {
		baseDao.delete(entity);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rol> getAll() {
		return (List<Rol>) baseDao.getAll(Rol.class);
	}

	@Override
	public Rol getById(Long entityId) {
		return (Rol) baseDao.getById(entityId, Rol.class);
	}

	public void delete(Long id)  throws DbException  {
		delete(getById(id));
	}

	@Transactional
	public PagingResult getAllByPaging(int first, int pageSize,SortOrder sortOrder, Map<String, Object> filters) {
		Session session = baseDao.getSession();
		Criteria criteria = session.createCriteria(Rol.class);
		if(filters.get("tanim")!=null){
			criteria.add(Restrictions.ilike("tanim",filters.get("tanim").toString(),  MatchMode.ANYWHERE));
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

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Rol> acompRol(String term) {
		Session session = baseDao.getSession();
		Criteria criteria = session.createCriteria(Rol.class);		
		criteria.add(Restrictions.or(Restrictions.ilike("kod",term,MatchMode.ANYWHERE)));		
		criteria.addOrder(Order.asc("kod"));
		return criteria.list();
	}

}
