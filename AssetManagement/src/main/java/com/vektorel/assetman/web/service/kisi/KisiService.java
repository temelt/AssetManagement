package com.vektorel.assetman.web.service.kisi;

import java.util.List;

import com.vektorel.assetman.web.entity.Kisi;
import com.vektorel.assetman.web.service.BaseDao;
import com.vektorel.assetman.web.utilities.IDataService;

public class KisiService implements IDataService<Kisi>{

	BaseDao baseDao=new BaseDao();
	
	
	@Override
	public Kisi save(Kisi entity) {
		return (Kisi)baseDao.save(entity);
		
	}

	@Override
	public Kisi update(Kisi entity) {
		return (Kisi)baseDao.update(entity);
	}

	@Override
	public boolean delete(Kisi entity) {
		return baseDao.delete(entity);
	}

	@Override
	public List<Kisi> getAll() {
		return (List<Kisi>) baseDao.getAll(Kisi.class);
	}

	@Override
	public Kisi getById(Long entityId) {
		return (Kisi) baseDao.getById(entityId, Kisi.class);
	}

	public void delete(Long id) {
		delete(getById(id));		
	}


}
