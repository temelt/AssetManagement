package com.vektorel.assetman.web.view.lokasyon;

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

import com.vektorel.assetman.web.entity.Lokasyon;
import com.vektorel.assetman.web.service.lokasyon.LokasyonService;
import com.vektorel.assetman.web.utilities.PagingResult;

//@ManagedBean
//@ViewScoped
//@Component
@Controller("lokasyonView")
@Scope("view")
public class LokasyonView implements Serializable{

/**
* 
*/
private static final long serialVersionUID = -6657286050378044309L;
@Autowired
private transient LokasyonService lokasyonService;
Lokasyon lokasyon;
LazyDataModel<Lokasyon> lazyModel;
public LokasyonView() {
super();
System.out.println("lokasyonView Oluþturuldu.");
}
@PostConstruct
private void init() {
lokasyon=new Lokasyon();
listele();
}
public void kaydet() {
try {
if(lokasyon.getId()!=null){
lokasyonService.update(lokasyon);
}else{
lokasyonService.save(lokasyon);
}
lokasyon=new Lokasyon();
listele();
} catch (Exception e) {
FacesContext context = FacesContext.getCurrentInstance();
       context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Hata Oluþtu",  e.getMessage()) );
}

}
public void guncelle(Long id) {
lokasyon=lokasyonService.getById(id);
}
public void sil(Long id) {
 lokasyonService.delete(id);
 lokasyon = new Lokasyon();
 listele();
}

public void listele(){
lazyModel=new LazyDataModel<Lokasyon>() {
/**
* 
*/
private static final long serialVersionUID = -8620783067167021310L;

@SuppressWarnings("unchecked")
@Override
public List<Lokasyon> load(int first, int pageSize, String sortField, SortOrder sortOrder,Map<String, Object> filters) {
PagingResult result = lokasyonService.getAllByPaging(first,pageSize,sortOrder , filters);
this.setRowCount(result.getRowCount());
return result.getList();
}
@Override
   public Lokasyon getRowData(String rowKey) {
       for(Lokasyon l : lazyModel) {
           if(l.getId().equals(rowKey))
               return l;
       }
 
       return null;
   }
};
}

public Lokasyon getLokasyon() {
return lokasyon;
}
public void setLokasyon(Lokasyon lokasyon) {
this.lokasyon = lokasyon;
}
public LazyDataModel<Lokasyon> getLazyModel() {
return lazyModel;
}
}

