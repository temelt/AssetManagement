package com.vektorel.assetman.web.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vektorel.assetman.web.entity.Yerlesim;
import com.vektorel.assetman.web.service.YerlesimService;

@Component("yerlesimConverter")
@Scope("request")
public class YerlesimConverter implements Converter {
	@Autowired
	private transient YerlesimService service;

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		try {
			if (value != null && value.trim().length() > 0) {
				return service.getById(Long.parseLong(value));
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		try {
			if (o != null)
				return String.valueOf(((Yerlesim) o).getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
