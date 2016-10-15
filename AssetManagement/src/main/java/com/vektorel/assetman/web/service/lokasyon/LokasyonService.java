package com.vektorel.assetman.web.service.lokasyon;

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

import com.vektorel.assetman.web.entity.Lokasyon;
import com.vektorel.assetman.web.service.BaseDao;
import com.vektorel.assetman.web.utilities.IDataService;
import com.vektorel.assetman.web.utilities.PagingResult;
import com.vektorel.assetman.web.utilities.ex.DbException;

@Service("LokasyonService")
public class LokasyonService implements IDataService<Lokasyon> {
	@Autowired
	private transient BaseDao baseDao;

	public LokasyonService() {
		super();
		System.out.println("LokasyonService Oluþturuldu.");
	}

	@Override
	public Lokasyon save(Lokasyon entity) throws DbException {
		return (Lokasyon) baseDao.save(entity);
	}

	public void delete(Long id) {
		try {
			delete(getById(id));
		} catch (DbException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Lokasyon update(Lokasyon entity) throws DbException {
		return (Lokasyon) baseDao.update(entity);
	}

	@Override
	public boolean delete(Lokasyon entity) throws DbException {
		return baseDao.delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lokasyon> getAll() {
		return (List<Lokasyon>) baseDao.getAll(Lokasyon.class);
	}

	@Override
	public Lokasyon getById(Long entityId) {
		return (Lokasyon) baseDao.getById(entityId, Lokasyon.class);
	}

	@Transactional
	public PagingResult getAllByPaging(int first, int pageSize,
			SortOrder sortOrder, Map<String, Object> filters) {
		Session session = baseDao.getSession();
		Criteria criteria = session.createCriteria(Lokasyon.class);
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

}