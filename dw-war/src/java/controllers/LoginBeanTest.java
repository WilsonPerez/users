/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dwEntities.UsuarioRole;
import dwManagers.UsuarioSessionLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Usuario-03
 */
@Named(value = "loginBeanTest")
@Stateless
public class LoginBeanTest implements Serializable {

    /**
     * Creates a new instance of LoginBeanTest
     */
    public LoginBeanTest() {
    }
    
    private static final long serialVersionUID = 1L;
    private String uname;
    private String password;
    private List<UsuarioRole> userRols;
    
    @EJB
    private UsuarioSessionLocal userSession;
     /**
     * Retorna la instancia del Managed Beans
     * @return --> la instancia
     */
    public static LoginBeanTest getInstance() {
        ELContext context = FacesContext.getCurrentInstance().getELContext();
        ValueExpression ex = FacesContext.getCurrentInstance().getApplication().getExpressionFactory().
                createValueExpression(context, "#{loginBeanTest}", LoginBeanTest.class);
        return (LoginBeanTest) ex.getValue(context);
    }

    public String loginProject() {
        userRols= validateUser(uname, password);
        if(userRols!=null){
            if(userRols.size()>0){
                addInfoMessage("Logeo exitoso", "");
                return "usuario?faces-redirect=true";
            }else{
                addInfoMessage("Usuario y contraseña erronea", "");
                return "faces/login.xhtml";
            }
        }else{
            addInfoMessage("Usuario y contraseña erronea", "");
            return "faces/login.xhtml";
        }
    }
    
    /**
     * Valida el login de usuario
     * @param name      //Name user
     * @param password  //Password user
     * @return          //Return true o False
     */
    public List<UsuarioRole> validateUser(String name, String password){
        List<UsuarioRole> userRols=new ArrayList<UsuarioRole>();
        try { 
            userRols= userSession.validateUser(name, password);
            System.out.println("Result UserValidate= "+userRols);
            
            for(int i=0; i<userRols.size(); i++){
                System.out.println("Result = "+userRols.get(i).getIdRole().getId()+", "+userRols.get(i).getIdRole().getDescripcion());
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return userRols;
    }
    
    //Method to add Information Message on page
    private void addInfoMessage(String a_msg, String a_msgDetail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, a_msg, a_msgDetail));
    }
    
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UsuarioRole> getUserRols() {
        return userRols;
    }

    public void setUserRols(List<UsuarioRole> userRols) {
        this.userRols = userRols;
    }
    
}
