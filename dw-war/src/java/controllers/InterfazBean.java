/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dwEntities.ComponenteGrafico;
import dwManagers.ComponenteGraficoSessionLocal;
import dwManagers.InterfazSessionLocal;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author root
 */
@Named(value = "interfazBean")
@SessionScoped
public class InterfazBean implements Serializable {
    @EJB
    private InterfazSessionLocal interfazSession;
    @EJB
    private ComponenteGraficoSessionLocal componenteSession;
    private List<ComponenteGrafico>items = null;
    private ComponenteGrafico currentItem;
    
    //Parent variables
    private Integer moduloId;
    private Map<String,Long> modulos;
    /**
     * Creates a new instance of InterfazBean
     */
    public InterfazBean() {
    }
    
    
    
}
