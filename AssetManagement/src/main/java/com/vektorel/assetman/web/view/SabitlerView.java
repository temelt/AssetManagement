package com.vektorel.assetman.web.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vektorel.assetman.web.entity.Kisi;
import com.vektorel.assetman.web.entity.Kullanici;
import com.vektorel.assetman.web.entity.Yerlesim;
import com.vektorel.assetman.web.entity.YerlesimTip;
import com.vektorel.assetman.web.service.YerlesimService;
import com.vektorel.assetman.web.service.kullanici.KullaniciService;
import com.vektorel.assetman.web.utilities.ex.DbException;

@Controller("sabitlerView")
@Scope("singleton")
public class SabitlerView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2357923778411951201L;

	@Autowired
	private transient YerlesimService yerlesimService;
	@Autowired
	private transient KullaniciService kullaniciService;
	
	private List<Yerlesim> ilListesi;
	@PostConstruct
	private void init() {
		ilListesi = yerlesimService.getAll(YerlesimTip.IL);		
		if(kullaniciService.count()<=1){
			Kullanici kullanici=new Kullanici();
			kullanici.setUsername("ttemel");
			kullanici.setPassword("123");
			Kisi k = new Kisi();
			k.setId(21L);
			kullanici.setKisi(k);
			try {
				kullaniciService.save(kullanici);
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Yerlesim> getIlListesi() {
		return ilListesi;
	}
}
