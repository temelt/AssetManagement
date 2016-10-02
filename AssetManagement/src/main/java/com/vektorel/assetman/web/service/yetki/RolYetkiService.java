package com.vektorel.assetman.web.service.yetki;

import java.util.List;

import com.vektorel.assetman.web.entity.RolYetki;
import com.vektorel.assetman.web.service.BaseDao;
import com.vektorel.assetman.web.utilities.IDataService;

public class RolYetkiService implements IDataService<RolYetki> {
	BaseDao baseDao = new BaseDao();

	@Override
	public RolYetki save(RolYetki entity) {
		return (RolYetki) baseDao.save(entity);
	}

	@Override
	public RolYetki update(RolYetki entity) {
		return (RolYetki) baseDao.update(entity);
	}

	@Override
	public boolean delete(RolYetki entity) {
		return baseDao.delete(entity);
	}

	@Override
	public List<RolYetki> getAll() {
		return (List<RolYetki>) baseDao.getAll(RolYetki.class);
	}

	@Override
	public RolYetki getById(Long entityId) {
		return (RolYetki) baseDao.getById(entityId, RolYetki.class);
	}

	public void delete(Long id) {
		delete(getById(id));
	}
}
