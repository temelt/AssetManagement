package com.vektorel.assetman.web.service.kullanici;

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

import com.vektorel.assetman.web.entity.Kullanici;
import com.vektorel.assetman.web.service.BaseDao;
import com.vektorel.assetman.web.utilities.IDataService;
import com.vektorel.assetman.web.utilities.PagingResult;
import com.vektorel.assetman.web.utilities.ex.DbException;

@Service("kullaniciService")
public class KullaniciService implements IDataService<Kullanici> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1613837182854052645L;
	@Autowired
	private transient BaseDao baseDao;

	@Override
	public Kullanici save(Kullanici entity) throws DbException {
		return (Kullanici) baseDao.save(entity);
	}

	@Override
	public Kullanici update(Kullanici entity) throws DbException {
		return (Kullanici) baseDao.update(entity);
	}

	public void delete(Long id) {
		try {
			delete(getById(id));
		} catch (DbException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean delete(Kullanici entity) throws DbException {
		return baseDao.delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Kullanici> getAll() {
		return baseDao.getAll(Kullanici.class);
	}

	@Override
	public Kullanici getById(Long entityId) {
		return (Kullanici) baseDao.getById(entityId, Kullanici.class);
	}

	@Transactional
	public PagingResult getAllByPaging(int first, int pageSize,
			SortOrder sortOrder, Map<String, Object> filters) {
		Session session = baseDao.getSession();
		Criteria criteria = session.createCriteria(Kullanici.class);

		if (filters.get("username") != null) {
			criteria.add(Restrictions.ilike("username", filters.get("username")
					.toString(), MatchMode.ANYWHERE));
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

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Kullanici> acompKullanici(String term) {
		Session session = baseDao.getSession();
		Criteria criteria = session.createCriteria(Kullanici.class);		
		criteria.add(Restrictions.or(Restrictions.ilike("username",term,MatchMode.ANYWHERE)));		
		criteria.addOrder(Order.asc("username"));
		return criteria.list();
	}

}