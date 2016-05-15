/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dwEntities.ComponenteGrafico;
import dwEntities.RolePermiso;
import dwEntities.Usuario;
import dwEntities.UsuarioRole;
import dwManagers.ComponenteGraficoSessionLocal;
import dwManagers.UsuarioSessionLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.primefaces.event.RowEditEvent;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author root
 */
@Named(value = "usuarioBean")
@Stateless
public class UsuarioBean implements Serializable {
    //private final HttpServletRequest httpServletRequest;
   // private final FacesContext faceContext;
   // private FacesMessage facesMessage;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private UsuarioSessionLocal usuarioSession;
    
    @EJB
    private ComponenteGraficoSessionLocal componenteGraficoSession;
    
    private List<Usuario>items = null;
    private Usuario currentItem;
    
    List<UsuarioRole> userRols=new ArrayList<UsuarioRole>();
    List<String> listComponentesGrafPermiso=new ArrayList<String>();
//    List<String> listComponentesGrafPermiso=new ArrayList<String>();
    /**
     * Creates a new instance of ModuloBean
     */
    public UsuarioBean() {

    }
    
    @PostConstruct
    public void init(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = request.getRequestURL().toString();
        
        List<ComponenteGrafico> listComponents=componenteGraficoSession.obtainAll();
        FacesContext context = FacesContext.getCurrentInstance();
        UIViewRoot root = context.getViewRoot();
//        List<String> listComponentesGraf=new ArrayList<String>();
        userRols=LoginBeanTest.getInstance().getUserRols();
        for(int i=0; i<userRols.size(); i++){
            System.out.println(".........Result = "+userRols.get(i).getIdRole().getId()+", "+userRols.get(i).getIdRole().getDescripcion());
        }
        
        for (int i=0; i<listComponents.size(); i++){
            if(url.substring(url.lastIndexOf("/")+1, url.length()).toLowerCase().compareTo(listComponents.get(i).getIdInterfaz().getNombre().toLowerCase())==0){
                
                List<RolePermiso> list=(List<RolePermiso>) listComponents.get(i).getRolePermisoCollection();
                for (int j=0; j<list.size(); j++){

                    for (int k=0; k<userRols.size(); k++){
                        if(list.get(j).getIdRole().getId().compareTo(userRols.get(k).getIdRole().getId())==0)
                            listComponentesGrafPermiso.add(list.get(j).getIdComponente().getNombre());
                    }
//                    listComponentesGraf.add(list.get(j).getIdComponente().getNombre());
                    System.out.println("cp: "+list.get(j).getIdComponente().getNombre());
                }
//                listComponentesGraf.add(listComponents.get(i).getNombre());
            }
            
            this.getClass().getDeclaredFields();
            System.out.println(listComponents.get(i).getIdInterfaz().getNombre());
            System.out.println(listComponents.get(i).getRolePermisoCollection());
        }        
    }
    public boolean getDisabled(String nameElement){
        boolean ban=true;
        for(int j=0; j<listComponentesGrafPermiso.size(); j++){
            if(listComponentesGrafPermiso.get(j).compareTo(nameElement)==0)
                ban=false;
        }
        return ban;
    }
    
    public String logoutProject()
    {
        //usuarioSession.getNextIdCampo()
        //userRols=LoginBeanTest.getInstance().getUserRols();
        LoginBeanTest.getInstance().setUname("");
        LoginBeanTest.getInstance().setPassword("");
        userRols.clear();
        return "faces/login.xhtml";
    }
    
    //<editor-fold defaultstate="collapsed" desc="GET - SET methods">
    public List<Usuario>getItems(){
        if(items==null){
            items = usuarioSession.obtainAll();
        }
        return items;
    }
    
    public void setItems(List<Usuario>items){
        this.items = items;
    }

    public Usuario getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(Usuario currentItem) {
        this.currentItem = currentItem;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Prepare view methods">
    private void recreateList(){
        items = null;
    }
    
    public String prepareList(){
        recreateList();
        return "usuario?faces-redirect=true";
    }
    
    public void prepareNewItem(){
        currentItem = new Usuario();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="CRUD methods">
    
    //Create new item method
    public void createItem() {
        Date hoy = new Date();
        
        try {   
            Long id = usuarioSession.getNextIdCampo();
            currentItem.setIdUsuario(id);
            currentItem.setEstado('1');
            currentItem.setFechaCreacion(hoy);
            usuarioSession.create(currentItem);
            recreateList();            
            addInfoMessage("Creado exitosamente", "");
        } catch (Exception e) {
            String lastCause = getLastCause(e);
            if (lastCause.contains("Entrada duplicada")) {//Personalised message for unique key error
                addErrorMessage("Un error ha ocurrido durante la creación", "Registro duplicado: Usuario ya existe.");
            } else {
                addErrorMessage("Un error ha ocurrido durante la creación", lastCause);
            }                     
        }
    }
    
    //Listener method for items table when editing one row
    public void onRowEdit(RowEditEvent event) {
        Usuario selectedItem = (Usuario) event.getObject();
        try {            
            usuarioSession.update(selectedItem);                        
            addInfoMessage("Actualizado exitosamente", "");       
        } catch (Exception e) {
            addErrorMessage("Un error ha ocurrido mientras se actualizaba el registro", getLastCause(e));
            
        }
    }
    
    //Delete item method when selected on items table       
    public void deleteItem() {         
        try {            
            usuarioSession.remove(currentItem);
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
    
     private UIComponent findComponent(UIComponent c, String id) {
    if (id.equals(c.getId())) {
      return c;
    }
    Iterator<UIComponent> kids = c.getFacetsAndChildren();
    while (kids.hasNext()) {
      UIComponent found = findComponent(kids.next(), id);
      if (found != null) {
        return found;
      }
    }
    return null;
  }
}
