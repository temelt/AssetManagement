package com.vektorel.assetman.web.service.rol;

import java.util.List;

import com.vektorel.assetman.web.service.BaseDao;
import com.vektorel.assetman.web.utilities.IDataService;

public class KullaniciRolService implements IDataService<KullaniciRolService> {
	BaseDao baseDao = new BaseDao();

	@Override
	public KullaniciRolService save(KullaniciRolService entity) {
		return (KullaniciRolService) baseDao.save(entity);
	}

	@Override
	public KullaniciRolService update(KullaniciRolService entity) {
		return (KullaniciRolService) baseDao.update(entity);
	}

	@Override
	public boolean delete(KullaniciRolService entity) {
		baseDao.delete(entity);
		return true;
	}

	@Override
	public List<KullaniciRolService> getAll() {
		return (List<KullaniciRolService>) baseDao.getAll(KullaniciRolService.class);
	}

	@Override
	public KullaniciRolService getById(Long entityId) {
		return (KullaniciRolService) baseDao.getById(entityId, KullaniciRolService.class);
	}

	public void delete(Long id) {
		delete(getById(id));
	}
}
