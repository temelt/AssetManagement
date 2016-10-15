package com.vektorel.assetman.web.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vektorel.assetman.web.entity.Yerlesim;
import com.vektorel.assetman.web.entity.YerlesimTip;
import com.vektorel.assetman.web.service.YerlesimService;

@Controller("sabitlerView")
@Scope("singleton")
public class SabitlerView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2357923778411951201L;

	@Autowired
	private transient YerlesimService yerlesimService;
	
	private List<Yerlesim> ilListesi;
	@PostConstruct
	private void init() {
		ilListesi = yerlesimService.getAll(YerlesimTip.IL);		
	}
	
	public List<Yerlesim> getIlListesi() {
		return ilListesi;
	}
}
