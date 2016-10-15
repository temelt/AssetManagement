package com.vektorel.assetman.web.service.yetki;

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

import com.vektorel.assetman.web.entity.RolYetki;
import com.vektorel.assetman.web.service.BaseDao;
import com.vektorel.assetman.web.utilities.IDataService;
import com.vektorel.assetman.web.utilities.PagingResult;
import com.vektorel.assetman.web.utilities.ex.DbException;

@Service("rolYetkiService")
public class RolYetkiService implements IDataService<RolYetki> {
	@Autowired
	private transient BaseDao baseDao;

	@Override
	public RolYetki save(RolYetki entity)  throws DbException{
		return (RolYetki) baseDao.save(entity);		
	}

	@Override
	public RolYetki update(RolYetki entity)  throws DbException {
		return (RolYetki) baseDao.update(entity);
	}

	@Override
	public boolean delete(RolYetki entity)  throws DbException {
		return baseDao.delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RolYetki> getAll() {
		return (List<RolYetki>) baseDao.getAll(RolYetki.class);
	}

	@Override
	public RolYetki getById(Long entityId) {
		return (RolYetki) baseDao.getById(entityId, RolYetki.class);
	}

	public void delete(Long id) throws DbException {
		delete(getById(id));
	}

	@Transactional
	public PagingResult getAllByPaging(int first, int pageSize,
			SortOrder sortOrder, Map<String, Object> filters) {
		Session session = baseDao.getSession();
		Criteria criteria = session.createCriteria(RolYetki.class);

		if (filters.get("adi") != null) {
			criteria.add(Restrictions.ilike("adi", filters.get("adi").toString(), MatchMode.ANYWHERE));
		}

		int totalResult = Integer.parseInt(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());

		criteria.setProjection(null);
		criteria.setMaxResults(pageSize);
		criteria.setFirstResult(first);
		criteria.addOrder(Order.desc("id"));
		return new PagingResult(criteria.list(), totalResult);
	}
}
