package com.vektorel.assetman.web.service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vektorel.assetman.web.utilities.Istatistik;

@Service
public class RaporService {

	private static final String kisiYilSayilari = "select count(id) as counts ,extract(year from dogumtarihi) as years  from gnl_kisi group by years order by years";
	
	@Autowired
	private DataSource dataSource;

	public List<Istatistik> getKisiYasGruplari() {
		try {
			Statement stmt = dataSource.getConnection().createStatement();
			ResultSet resultSet = stmt.executeQuery(kisiYilSayilari);
			List<Istatistik> result =new ArrayList<Istatistik>();
			while(resultSet.next()){
				result.add(new Istatistik(resultSet.getBigDecimal("years").toString(), resultSet.getBigDecimal("counts").toString()));
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
