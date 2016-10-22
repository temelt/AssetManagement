package com.vektorel.assetman.web.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import net.webservicex.Country;
import net.webservicex.CountrySoap;
import net.webservicex.GetISD;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vektorel.assetman.web.service.RaporService;
import com.vektorel.assetman.web.utilities.Istatistik;

@Controller("indexView")
@Scope("view")
public class IndexView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1152435119548646166L;

	@Autowired
	private transient RaporService raporService;

	List<Istatistik> kisiIstatistigi;
	BarChartModel model;
	LineChartModel lineChartModel;
	private String ulke;
	private String ulkeTelKodu;

	@PostConstruct
	private void init() {
		kisiIstatistigi = raporService.getKisiYasGruplari();
		chartDatasiOlustur();
	}

	private void chartDatasiOlustur() {
		model = new BarChartModel();
		lineChartModel = new LineChartModel();
		ChartSeries sayilar = new ChartSeries();
		sayilar.setLabel("Kiþi Sayýlarý");

		for (Istatistik ist : kisiIstatistigi) {
			sayilar.set(Integer.valueOf(ist.getKey()),
					Integer.valueOf(ist.getValue()));
		}

		model.addSeries(sayilar);
		lineChartModel.addSeries(sayilar);
	}

	public void telefonKodu() {
		CountrySoap countrySoap = (CountrySoap) new Country().getCountrySoap();
		ulkeTelKodu = countrySoap.getISD(getUlke());
	}

	public List<Istatistik> getKisiIstatistigi() {
		return kisiIstatistigi;
	}

	public BarChartModel getModel() {
		return model;
	}

	public LineChartModel getLineChartModel() {
		return lineChartModel;
	}

	public String getUlke() {
		return ulke;
	}

	public void setUlke(String ulke) {
		this.ulke = ulke;
	}

	public String getUlkeTelKodu() {
		return ulkeTelKodu;
	}

}
