/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dwEntities.DwModulo;
import dwEntities.UsuarioRole;
import dwManagers.ModuloSessionLocal;
import javax.inject.Named;
// import javax.enterprise.context.SessionScoped;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author root
 */
@Named(value = "moduloBean")
@SessionScoped
public class ModuloBean implements Serializable {

    @EJB
    private ModuloSessionLocal moduloSession;
    private List<DwModulo>items = null;
    private DwModulo currentItem;
    List<UsuarioRole> userRols=new ArrayList<UsuarioRole>();
    /**
     * Creates a new instance of ModuloBean
     */
    public ModuloBean() {
        userRols=LoginBeanTest.getInstance().getUserRols();
        for(int i=0; i<userRols.size(); i++){
                System.out.println("..n.......Result = "+userRols.get(i).getIdRole().getId()+", "+userRols.get(i).getIdRole().getDescripcion());
            }
        
    }
    
    @PostConstruct
    public void init(){
        userRols=LoginBeanTest.getInstance().getUserRols();
        for(int i=0; i<userRols.size(); i++){
                System.out.println(".........Result = "+userRols.get(i).getIdRole().getId()+", "+userRols.get(i).getIdRole().getDescripcion());
            }
    }
    
    //<editor-fold defaultstate="collapsed" desc="GET - SET methods">
    public List<DwModulo>getItems(){
        if(items==null){
            items = moduloSession.obtainAll();
        }
        return items;
    }
    
    public void setItems(List<DwModulo>items){
        this.items = items;
    }

    public DwModulo getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(DwModulo currentItem) {
        this.currentItem = currentItem;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Prepare view methods">
    private void recreateList(){
        items = null;
    }
    
    public String prepareList(){
        recreateList();
        return "modulo?faces-redirect=true";
    }
    
    public void prepareNewItem(){
        currentItem = new DwModulo();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="CRUD methods">
    
    //Create new item method
    public void createItem() {
        try {   
            Long id = moduloSession.getNextIdCampo();
            currentItem.setId(id);
            moduloSession.create(currentItem);
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
        DwModulo selectedItem = (DwModulo) event.getObject();
        try {            
            moduloSession.update(selectedItem);                        
            addInfoMessage("Actualizado exitosamente", "");       
        } catch (Exception e) {
            addErrorMessage("Un error ha ocurrido mientras se actualizaba el registro", getLastCause(e));
            
        }
    }
    
    //Delete item method when selected on items table       
    public void deleteItem() {         
        try {            
            moduloSession.remove(currentItem);
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
