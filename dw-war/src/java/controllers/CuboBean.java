/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dwEntities.DwCubo;
import dwEntities.DwModulo;
import dwManagers.CuboSessionLocal;
import dwManagers.ModuloSessionLocal;
import javax.inject.Named;
//import javax.enterprise.context.SessionScoped;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author root
 */
@Named(value = "cuboBean")
@SessionScoped
public class CuboBean implements Serializable {

    @EJB
    private ModuloSessionLocal moduloSession;
    @EJB
    private CuboSessionLocal cuboSession;
    private List<DwCubo>items = null;
    private DwCubo currentItem;
    
    //Parent variables
    private Integer moduloId;
    private Map<String,Long> modulos;
    /**
     * Creates a new instance of ModuloBean
     */
    public CuboBean() {
    }
    
    //<editor-fold defaultstate="collapsed" desc="GET - SET methods">
    public List<DwCubo>getItems(){
        if(items==null){
            items = cuboSession.obtainAll();
        }
        return items;
    }
    
    public void setItems(List<DwCubo>items){
        this.items = items;
    }

    public DwCubo getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(DwCubo currentItem) {
        this.currentItem = currentItem;
    }

    public Integer getModuloId() {
        return moduloId;
    }

    public void setModuloId(Integer moduloId) {
        this.moduloId = moduloId;
    }

    public Map<String, Long> getModulos() {
        return modulos;
    }
    
       
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Prepare view methods">
    private void recreateList(){
        items = null;
    }
    
    public String prepareList(){
        recreateList();
        prepareModulos();
        return "cubo?faces-redirect=true";
    }
    
    public void prepareNewItem(){
        currentItem = new DwCubo();
        prepareModulos();
        moduloId=-1;
    }
    
    public void prepareModulos(){
        modulos = new LinkedHashMap<>();
        List<DwModulo> moduloList = moduloSession.obtainAll();
        for (DwModulo dwModulo : moduloList) {
            modulos.put(dwModulo.getNombre(),dwModulo.getId());
        }
        
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="CRUD methods">
    
    //Create new item method
    public void createItem() {
        try {               
            Long id = cuboSession.getNextIdCampo();
            currentItem.setId(id);
            DwModulo aModulo = moduloSession.obtainById(moduloId);
            currentItem.setIdDwModulo(aModulo);
            cuboSession.create(currentItem);
            recreateList();            
            addInfoMessage("Creado exitosamente", "");
        } catch (Exception e) {
            String lastCause = getLastCause(e);
            if (lastCause.contains("Entrada duplicada")) {//Personalised message for unique key error
                addErrorMessage("Un error ha ocurrido durante la creación", "Registro duplicado: Módulo ya existe.");
            } else {
                addErrorMessage("Un error ha ocurrido durante la creación", lastCause);
            }                     
        }
    }
    
    //Listener method for items table when editing one row
    public void onRowEdit(RowEditEvent event) {
        DwCubo selectedItem = (DwCubo) event.getObject();
        try {            
            cuboSession.update(selectedItem);                        
            addInfoMessage("Actualizado exitosamente", "");       
        } catch (Exception e) {
            addErrorMessage("Un error ha ocurrido mientras se actualizaba el registro", getLastCause(e));
            
        }
    }
    
    //Delete item method when selected on items table       
    public void deleteItem() {         
        try {            
            cuboSession.remove(currentItem);
            recreateList();
            addInfoMessage("Eliminado exitosamente", "");            
        } catch (Exception e) {            
            addErrorMessage("Un error ha ocurrido mientra se eliminaba el registro", getLastCause(e));            
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Message methods">
    //Method to add Information Message on page
    private void addInfoMessage(String a_msg, String a_msgDetail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, a_msg, a_msgDetail));
    }

    //Method to add Error Message on page
    private void addErrorMessage(String a_msg, String a_msgDetail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, a_msg, a_msgDetail));
    }

    //Recursive Method to obtain description of last error cause on error stack
    private String getLastCause(Throwable a_ex) {
        Throwable cause = a_ex;
        if (a_ex.getCause() == null) {
            return cause.toString();
        } else {
            return getLastCause(a_ex.getCause());
        }
    }
    //</editor-fold>
}
