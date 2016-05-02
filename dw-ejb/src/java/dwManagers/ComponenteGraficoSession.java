/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dwManagers;

import dwEntities.ComponenteGrafico;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author root
 */
@Stateless
public class ComponenteGraficoSession implements ComponenteGraficoSessionLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
     @PersistenceContext(unitName = "dw-ejbPU")
    private EntityManager em;

    public void create(ComponenteGrafico componente) {
        em.persist(componente);
    }

    public void update(ComponenteGrafico componente) {
        em.merge(componente);
    }
    
    public void remove(ComponenteGrafico componente) {
        em.remove(em.merge(componente));
    }
    
    public List<ComponenteGrafico> obtainAll(){
        return em.createNamedQuery("ComponenteGrafico.findAll").getResultList();
    }
    
    public ComponenteGrafico obtainById(Integer id){
        return (ComponenteGrafico) em.createNamedQuery("ComponenteGrafico.findById").
                setParameter("id", id).getSingleResult();
    }
    
    //Obtener maximo id
    public Long getNextIdCampo() {
        return getMaximoId("componente_grafico", "id");
    }
    
    private Long getMaximoId(String iTabla, String iIdentificador) {
        Long aux = (Long) em.createNativeQuery("SELECT MAX(" + iIdentificador + ") "
                + "FROM " + iTabla).getSingleResult();

        if(aux == null){
            return Long.valueOf("1");
        }
        return aux + 1;

    }
}
