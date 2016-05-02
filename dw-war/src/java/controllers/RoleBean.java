/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dwEntities.Role;
import dwManagers.RoleSessionLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author root
 */
@Named(value = "roleBean")
@Stateless
public class RoleBean implements Serializable {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
     @EJB
    private RoleSessionLocal roleSession;
    private List<Role>items = null;
    private Role currentItem;
    /**
     * Creates a new instance of ModuloBean
     */
    public RoleBean() {
    }
    
    //<editor-fold defaultstate="collapsed" desc="GET - SET methods">
    public List<Role>getItems(){
        if(items==null){
            items = roleSession.obtainAll();
        }
        return items;
    }
    
    public void setItems(List<Role>items){
        this.items = items;
    }

    public Role getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(Role currentItem) {
        this.currentItem = currentItem;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Prepare view methods">
    private void recreateList(){
        items = null;
    }
    
    public String prepareList(){
        recreateList();
        return "role?faces-redirect=true";
    }
    
    public void prepareNewItem(){
        currentItem = new Role();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="CRUD methods">
    
    //Create new item method
    public void createItem() {
        try {   
            Long id = roleSession.getNextIdCampo();
            currentItem.setId(id);
            roleSession.create(currentItem);
            recreateList();            
            addInfoMessage("Creado exitosamente", "");
        } catch (Exception e) {
            String lastCause = getLastCause(e);
            if (lastCause.contains("Entrada duplicada")) {//Personalised message for unique key error
                addErrorMessage("Un error ha ocurrido durante la creación", "Registro duplicado: Role ya existe.");
            } else {
                addErrorMessage("Un error ha ocurrido durante la creación", lastCause);
            }                     
        }
    }
    
    //Listener method for items table when editing one row
    public void onRowEdit(RowEditEvent event) {
        Role selectedItem = (Role) event.getObject();
        try {            
            roleSession.update(selectedItem);                        
            addInfoMessage("Actualizado exitosamente", "");       
        } catch (Exception e) {
            addErrorMessage("Un error ha ocurrido mientras se actualizaba el registro", getLastCause(e));
            
        }
    }
    
    //Delete item method when selected on items table       
    public void deleteItem() {         
        try {            
            roleSession.remove(currentItem);
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
