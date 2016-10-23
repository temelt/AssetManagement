package com.vektorel.assetman.web.ws;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


//AssetManagement/rest/kisi/
@Path("/kisi")
public class KisiJsonService {

	@GET
	@Produces("application/xml; charset=UTF-8")
	public Response get() {

		List<WsKisi> kisis=new ArrayList<WsKisi>();
		
		kisis.add(new WsKisi(1L, "ahmet", "temel", new Date()));
		kisis.add(new WsKisi(1L, "ahmet2", "temel2", new Date()));

		return Response.status(200).entity(kisis).build();

	}
	
}
