/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dwManagers;

import dwEntities.Interfaz;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class InterfazSession implements InterfazSessionLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "dw-ejbPU")
    private EntityManager em;

    public void create(Interfaz cubo) {
        em.persist(cubo);
    }

    public void update(Interfaz cubo) {
        em.merge(cubo);
    }
    
    public void remove(Interfaz cubo) {
        em.remove(em.merge(cubo));
    }
    
    public List<Interfaz> obtainAll(){
        return em.createNamedQuery("Interfaz.findAll").getResultList();
    }
    
    public Interfaz obtainById(Integer id){
        return (Interfaz) em.createNamedQuery("Interfaz.findById").
                setParameter("id", id).getSingleResult();
    }
    
    //Obtener maximo id
    public Long getNextIdCampo() {
        return getMaximoId("interfaz", "id");
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
