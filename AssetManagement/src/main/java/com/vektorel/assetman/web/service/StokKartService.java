package com.vektorel.assetman.web.service;

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

import com.vektorel.assetman.web.entity.StokKart;
import com.vektorel.assetman.web.utilities.IDataService;
import com.vektorel.assetman.web.utilities.PagingResult;
import com.vektorel.assetman.web.utilities.ex.DbException;

@Service("stokKartService")
public class StokKartService implements IDataService<StokKart> {

	@Autowired
	private transient BaseDao baseDao;

	public StokKartService() {
		super();
		System.out.println("StokKartService Oluþturuldu.");

	}

	@Override
	public StokKart save(StokKart entity) throws DbException {

		return (StokKart) baseDao.save(entity);
	}

	@Override
	public StokKart update(StokKart entity) throws DbException {

		return (StokKart) baseDao.update(entity);
	}

	@Override
	public boolean delete(StokKart entity) throws DbException {

		return baseDao.delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StokKart> getAll() {
		return (List<StokKart>) baseDao.getAll(StokKart.class);
	}

	@Override
	public StokKart getById(Long entityId) {
		return (StokKart) baseDao.getById(entityId, StokKart.class);

	}

	public void delete(Long id) throws DbException {
		delete(getById(id));
	}

	@Transactional
	public PagingResult getAllByPaging(int first, int pageSize,
			SortOrder sortOrder, Map<String, Object> filters) {
		Session session = baseDao.getSession();
		Criteria criteria = session.createCriteria(StokKart.class);

		if (filters.get("tanim") != null) {
			criteria.add(Restrictions.ilike("tanim", filters.get("tanim")
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

	@SuppressWarnings("unchecked")
	public List<StokKart> acompStokKart(String term) {
		Session session = baseDao.getSession();
		Criteria criteria = session.createCriteria(StokKart.class);
		criteria.add(Restrictions.or(Restrictions.ilike("adi", term,
				MatchMode.ANYWHERE)));
		criteria.addOrder(Order.asc("adi"));
		return criteria.list();
	}

}