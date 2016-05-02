/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dwManagers;

import dwEntities.DwModulo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class ModuloSession implements ModuloSessionLocal {

    @PersistenceContext(unitName = "dw-ejbPU")
    private EntityManager em;

    public void create(DwModulo modulo) {
        em.persist(modulo);
    }

    public void update(DwModulo modulo) {
        em.merge(modulo);
    }
    
    public void remove(DwModulo modulo) {
        em.remove(em.merge(modulo));
    }
    
    public List<DwModulo> obtainAll(){
        return em.createNamedQuery("DwModulo.findAll").getResultList();
    }
    
    public DwModulo obtainById(Integer id){
        return (DwModulo) em.createNamedQuery("DwModulo.findById").
                setParameter("id", id).getSingleResult();
    }
    
    //Obtener maximo id
    public Long getNextIdCampo() {
        return getMaximoId("dw_modulo", "id");
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
