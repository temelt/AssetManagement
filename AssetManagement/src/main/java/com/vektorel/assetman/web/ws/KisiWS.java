package com.vektorel.assetman.web.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vektorel.assetman.web.entity.Kisi;
import com.vektorel.assetman.web.service.kisi.KisiService;

@WebService(name="KisiWS",serviceName="KisiService")
@Service
public class KisiWS {

	@Autowired
	private transient KisiService kisiService;
	
	@WebMethod
	public WsKisi getKisiByTc( String tc) {
		Kisi kisiDb = kisiService.getByTC(tc);		
		return new WsKisi(kisiDb.getId(), kisiDb.getAd(), kisiDb.getSoyad(), kisiDb.getDogumTarihi());
	}
	
	
	@WebMethod()
	public boolean saveKisi( WsKisi kisi) {
		try {			
			Kisi kDb =new Kisi();
			kDb.setAd(kisi.getAd());
			kDb.setSoyad(kisi.getSoyad());
			kDb.setDogumTarihi(kisi.getDogumTarihi());
			kisiService.save(kDb);			
		} catch (Exception e) {
			return false;
		}	
		
		return true;
	}
}



//C:\TTEMEL\Projects\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\AssetManagement\WEB-INF\classes>wsgen  -verbose -keep -cp . com.vektorel.assetman.web.ws.KisiWS

//
//public static void main(String[] args) {
//	try {
//		KisiWSProxy service = new KisiWSProxy("http://localhost:8081/KisiService?wsdl");
//		WsKisi returnData = service.getKisiByTc("asdf");
//		System.out.println("Ad :" + returnData.getAd() + "\nSoyad :"+ returnData.getSoyad());
//		
//		
//		
//		
//		WsKisi kisi=new WsKisi();
//		kisi.setAd("Binali");
//		kisi.setCinsiyet(true);
//		kisi.setDogumTarihi(Calendar.getInstance());
//		kisi.setSoyad("YILDIRIM");
//		
//		boolean sonuc = service.saveKisi(kisi);
//		
//		if(sonuc){
//			System.out.println("Kayýt Eklendi");
//		}
//	} catch (Exception e) {
//	}
//}