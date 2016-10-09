package com.vektorel.assetman.web.view.yetki;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vektorel.assetman.web.entity.RolYetki;
import com.vektorel.assetman.web.entity.Yetki;
import com.vektorel.assetman.web.service.yetki.YetkiService;
import com.vektorel.assetman.web.utilities.PagingResult;
import com.vektorel.assetman.web.utilities.ex.DbException;


@Controller("yetkiView")
@Scope("view")
public class YetkiView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LazyDataModel<Yetki> lazyModel;
	Yetki yetki = null;
	
	@Autowired
	private transient YetkiService yetkiService ;

	@PostConstruct
	private void init() {

		yetki = new Yetki();
		listele();
	}

	public void kaydet() {
		try {
			if (yetki.getId() == null) {
				yetki.setEklemeTarihi(new Date());
				yetkiService.save(yetki);
				yetki = new Yetki();
				listele();
		        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bilgilendirme", "Baþarýyla Kaydedildi."));
			}else{
				yetki.setGuncellemeTarihi(new Date());
				yetkiService.update(yetki);
				yetki = new Yetki();
				listele();
		        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bilgilendirme", "Baþarýyla Güncellendi."));
			}
		} catch (DbException e) {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Hata Oluþtu",  e.getMessage()) );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sil(Long rolId) {
		try {
			yetkiService.delete(rolId);
			yetki = new Yetki();
			listele();
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bilgilendirme", "Baþarýyla Silindi."));
		}catch (DbException e) {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Hata Oluþtu",  e.getMessage()) );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void listele() {
		lazyModel = new LazyDataModel<Yetki>() {
			@Override
			public List<Yetki> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters) {
				PagingResult result = yetkiService.getAllByPaging(first,
						pageSize, sortOrder, filters);
				this.setRowCount(result.getRowCount());
				return result.getList();
			}
			 @Override
			    public Yetki getRowData(String rowKey) {
			        for(Yetki y : lazyModel) {
			            if(y.getId().equals(rowKey))
			                return y;
			        }
			 
			        return null;
			    }
		};

	}

	public LazyDataModel<Yetki> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Yetki> lazyDataModel) {
		this.lazyModel = lazyDataModel;
	}

	public Yetki getYetki() {
		return yetki;
	}

	public void setYetki(Yetki yetki) {
		this.yetki = yetki;
	}

	public void getById(Long id) {
		yetki = yetkiService.getById(id);
	}
}
