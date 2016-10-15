package com.vektorel.assetman.web.view.personel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vektorel.assetman.web.entity.Personel;
import com.vektorel.assetman.web.service.personel.PersonelService;
import com.vektorel.assetman.web.utilities.PagingResult;
import com.vektorel.assetman.web.utilities.ex.DbException;

@Controller("personelView")
@Scope("view")
public class PersonelView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7888257704627682510L;

	@Autowired
	private transient PersonelService personelService;
	Personel personel;
	LazyDataModel<Personel> lazyModel; // neden Personel(personel) yazdýk?

	@PostConstruct
	private void init() {

		personel = new Personel();
		listele();
	}

	public void kaydet() {
		try {
			if (personel.getId() != null) {
				personelService.update(personel);
			} else {
				personelService.save(personel);
			}
			personel = new Personel();
			listele();
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata Oluþtu", e.getMessage()));
		}

	}

	public void guncelle(Long id) {
		personel = personelService.getById(id);
	}

	public void sil(Long id) throws DbException {
		personelService.delete(id);
		personel = new Personel();
		listele();
	}

	public void listele() {
		lazyModel = new LazyDataModel<Personel>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 8730045803889568049L;

			@SuppressWarnings("unchecked")
			public List<Personel> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				PagingResult result = personelService.getAllByPaging(first, pageSize, sortOrder, filters);
				this.setRowCount(result.getRowCount());
				return result.getList();
			}

			@Override
			public Personel getRowData(String rowKey) {
				for (Personel k : lazyModel) {
					if (k.getId().equals(rowKey))
						return k;
				}

				return null;
			}

		};
	}

	public Personel getPersonel() {
		return personel;
	}

	public void setPersonel(Personel personel) {
		this.personel = personel;
	}

	public LazyDataModel<Personel> getLazyModel() {
		return lazyModel;
	}
}
