package com.vektorel.assetman.web.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vektorel.assetman.web.entity.Kisi;
import com.vektorel.assetman.web.service.kisi.KisiService;

@Component("kisiConverter")
@Scope("request")
public class KisiConverter implements Converter{
	@Autowired
	private transient KisiService service;
	   public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
	        if(value != null && value.trim().length() > 0) {
	            try {
	                return service.getById(Long.parseLong(value));
	            } catch(NumberFormatException e) {
	               e.printStackTrace();
	               return null;
	            }
	        }
	        else {
	            return null;
	        }
	    }
	 
	    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
	        if(o != null) {
	            return String.valueOf(((Kisi) o).getId());
	        }
	        else {
	            return null;
	        }
	    } 

}
