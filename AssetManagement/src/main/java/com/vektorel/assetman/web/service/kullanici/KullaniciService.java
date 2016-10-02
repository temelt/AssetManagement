package com.vektorel.assetman.web.service.kullanici;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;

import com.vektorel.assetman.web.entity.Kullanici;
import com.vektorel.assetman.web.service.BaseDao;
import com.vektorel.assetman.web.utilities.IDataService;
import com.vektorel.assetman.web.utilities.PagingResult;
import com.vektorel.assetman.web.utilities.ex.DbException;

public class KullaniciService implements IDataService<Kullanici>{

	BaseDao baseDao=new BaseDao();
	
	@Override
	public Kullanici save(Kullanici entity) throws DbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Kullanici update(Kullanici entity) throws DbException {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(Long id) {
		
		try {
			delete(getById(id));
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Override
	public boolean delete(Kullanici entity) throws DbException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Kullanici> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Kullanici getById(Long entityId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public PagingResult getAllByPaging(int first, int pageSize, SortOrder sortOrder, Map<String, Object> filters) {		
		Session session = baseDao.getSession();
		Criteria criteria = session.createCriteria(Kullanici.class);
		
		if(filters.get("username")!=null){
			criteria.add(Restrictions.ilike("username",filters.get("username").toString(),  MatchMode.ANYWHERE));
		}
		
		int totalResult = Integer.parseInt(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
		
		criteria.setProjection(null);	
		criteria.setMaxResults(pageSize);
		criteria.setFirstResult(first);
		criteria.addOrder(Order.desc("id"));
		return new PagingResult(criteria.list(), totalResult);
	}

}
