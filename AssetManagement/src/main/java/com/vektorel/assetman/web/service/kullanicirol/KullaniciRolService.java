package com.vektorel.assetman.web.service.kullanicirol;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;

import com.vektorel.assetman.web.service.BaseDao;
import com.vektorel.assetman.web.utilities.IDataService;
import com.vektorel.assetman.web.utilities.PagingResult;
import com.vektorel.assetman.web.utilities.ex.DbException;

public class KullaniciRolService<KullaniciRol> implements IDataService<KullaniciRol> {

	BaseDao baseDao=new BaseDao();
	
	@SuppressWarnings("unchecked")
	@Override
	public KullaniciRol save(KullaniciRol entity) throws DbException {
		return (KullaniciRol)baseDao.save(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public KullaniciRol update(KullaniciRol entity) throws DbException {
		return (KullaniciRol) baseDao.update(entity);
	}

	@Override
	public boolean delete(KullaniciRol entity) throws DbException {
		return baseDao.delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KullaniciRol> getAll() {
		return (List<KullaniciRol>)baseDao.getAll(com.vektorel.assetman.web.entity.KullaniciRol.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public KullaniciRol getById(Long entityId) {
		return (KullaniciRol)baseDao.getById(entityId, com.vektorel.assetman.web.entity.KullaniciRol.class);
	}

	public void delete(Long id) throws DbException{
		delete(getById(id));
	}

	public PagingResult getAllByPaging(int first, int pageSize,
			SortOrder sortOrder, Map<String, Object> filters) {
		Session s = baseDao.getSession();
		Criteria criteria = s.createCriteria(com.vektorel.assetman.web.entity.KullaniciRol.class);
		if (filters.get("username") != null) {
			criteria.add(Restrictions.ilike("username",filters.get("username").toString(), MatchMode.ANYWHERE));
		}
		int totalResult = Integer.parseInt(criteria
				.setProjection(Projections.rowCount()).uniqueResult()
				.toString());

		criteria.setProjection(null);
		criteria.setMaxResults(pageSize);
		criteria.setFirstResult(first);
		criteria.addOrder(Order.desc("id"));
		return new PagingResult(criteria.list(), totalResult);
	}
}
