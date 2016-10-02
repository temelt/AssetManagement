package com.vektorel.assetman.web.service.rol;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.primefaces.model.SortOrder;

import com.vektorel.assetman.web.entity.Rol;
import com.vektorel.assetman.web.service.BaseDao;
import com.vektorel.assetman.web.utilities.IDataService;
import com.vektorel.assetman.web.utilities.PagingResult;

public class RolService implements IDataService<Rol> {
	BaseDao baseDao = new BaseDao();

	@Override
	public Rol save(Rol entity) {
		return (Rol) baseDao.save(entity);
	}

	@Override
	public Rol update(Rol entity) {
		return (Rol) baseDao.update(entity);
	}

	@Override
	public boolean delete(Rol entity) {
		baseDao.delete(entity);
		return true;
	}

	@Override
	public List<Rol> getAll() {
		return (List<Rol>) baseDao.getAll(Rol.class);
	}

	@Override
	public Rol getById(Long entityId) {
		return (Rol) baseDao.getById(entityId, Rol.class);
	}

	public void delete(Long id) {
		delete(getById(id));
	}

	public PagingResult getAllByPaging(int first, int pageSize,SortOrder sortOrder, Map<String, Object> filters) {
		Session session = baseDao.getSession();
		Criteria criteria = session.createCriteria(Rol.class);
		
		int totalResult = Integer.parseInt(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
		
		criteria.setProjection(null);	
		criteria.setMaxResults(pageSize);
		criteria.setFirstResult(first);
		criteria.addOrder(Order.desc("id"));
		return new PagingResult(criteria.list(), totalResult);
	}

}
