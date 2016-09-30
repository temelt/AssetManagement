package com.vektorel.assetman.web.service.yazilim;

import java.util.List;

import com.vektorel.assetman.web.entity.Yazilim;
import com.vektorel.assetman.web.service.BaseDao;
import com.vektorel.assetman.web.utilities.IDataService;

public class YazilimService implements IDataService<Yazilim> {

	BaseDao baseDao = new BaseDao();

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
