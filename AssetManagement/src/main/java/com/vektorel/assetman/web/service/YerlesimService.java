package com.vektorel.assetman.web.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vektorel.assetman.web.entity.Yerlesim;
import com.vektorel.assetman.web.entity.YerlesimTip;
import com.vektorel.assetman.web.utilities.ex.DbException;

@Service("yerlesimService")
public  class YerlesimService {

	@Autowired
	private transient BaseDao baseDao;

	public Yerlesim save(Yerlesim entity) throws DbException {
		return null;
	}

	public Yerlesim update(Yerlesim entity) throws DbException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean delete(Yerlesim entity) throws DbException {
		// TODO Auto-generated method stub
		return false;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Yerlesim> getAll(YerlesimTip tip) {
		Criteria criteria=baseDao.getSession().createCriteria(Yerlesim.class);
		criteria.add(Restrictions.eq("yerlesimTip", tip));
		return  criteria.list();
	}

	public Yerlesim getById(Long entityId) {
		return (Yerlesim) baseDao.getById(entityId, Yerlesim.class);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Yerlesim> getByParentId(Long entityId) {
		Criteria criteria=baseDao.getSession().createCriteria(Yerlesim.class);
		criteria.add(Restrictions.eq("ustYerlesim.id", entityId));
		return  criteria.list();
	}
	
	
}
