package com.vektorel.assetman.web.view.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vektorel.assetman.web.entity.Rol;
import com.vektorel.assetman.web.service.rol.RolService;

@Component("rolConverter")
@Scope("request")
public class RolConverter implements Converter, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7093681760858753209L;
	@Autowired
	private transient RolService rolService;

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				return rolService.getById(Long.parseLong(value));
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		if (o != null) {
			return String.valueOf(((Rol) o).getId());
		} else {
			return null;
		}
	}

}
