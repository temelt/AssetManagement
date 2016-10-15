package com.vektorel.assetman.web.service.yazilim;

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

import com.vektorel.assetman.web.entity.Yazilim;
import com.vektorel.assetman.web.service.BaseDao;
import com.vektorel.assetman.web.utilities.IDataService;
import com.vektorel.assetman.web.utilities.PagingResult;

@Service("yazilimService")
public class YazilimService implements IDataService<Yazilim> {

	@Autowired
	private transient BaseDao baseDao;

	@Override
	public Yazilim save(Yazilim entity) {
		return (Yazilim) baseDao.save(entity);

	}

	@Override
	public Yazilim update(Yazilim entity) {
		return (Yazilim) baseDao.update(entity);
	}

	@Override
	public boolean delete(Yazilim entity) {
		return baseDao.delete(entity);
	}

	@Transactional
	public PagingResult getAllByPaging(int first, int pageSize,
			SortOrder sortOrder, Map<String, Object> filters) {
		Session session = baseDao.getSession();
		Criteria criteria = session.createCriteria(Yazilim.class);
		if (filters.get("ad") != null) {
			criteria.add(Restrictions.ilike("ad", filters.get("ad").toString(),
					MatchMode.ANYWHERE));
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
	@Override
	public List<Yazilim> getAll() {
		return (List<Yazilim>) baseDao.getAll(Yazilim.class);
	}

	@Override
	public Yazilim getById(Long entityId) {
		return (Yazilim) baseDao.getById(entityId, Yazilim.class);
	}

	public void delete(Long id) {
		delete(getById(id));
	}

}