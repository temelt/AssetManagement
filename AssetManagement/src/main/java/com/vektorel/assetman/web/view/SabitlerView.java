package com.vektorel.assetman.web.view;

import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import net.webservicex.Country;
import net.webservicex.CountrySoap;
import net.webservicex.NewDataSet;
import net.webservicex.Table;

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
public class SabitlerView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2357923778411951201L;

	@Autowired
	private transient YerlesimService yerlesimService;
	@Autowired
	private transient KullaniciService kullaniciService;

	private List<Yerlesim> ilListesi;
	private List<String> ulkeListesi;

	@PostConstruct
	private void init() {
		ulkeListesi=new ArrayList<>();
		ilListesi = yerlesimService.getAll(YerlesimTip.IL);
		if (kullaniciService.count() <= 1) {
			Kullanici kullanici = new Kullanici();
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

		CountrySoap countrySoap = (CountrySoap) new Country().getCountrySoap();
		String countries = countrySoap.getCountries();
		marshall(countries);

	}

	private void marshall(String countries) {

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(NewDataSet.class);
			Unmarshaller jaxbMarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(countries);
			NewDataSet dataset = (NewDataSet) jaxbMarshaller.unmarshal(reader);
			for (Table element : dataset.getData()) {
				ulkeListesi.add(element.getUlke());
			}

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public List<Yerlesim> getIlListesi() {
		return ilListesi;
	}

	public List<String> getUlkeListesi() {
		return ulkeListesi;
	}
}
