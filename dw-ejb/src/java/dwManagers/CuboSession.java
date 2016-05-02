/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dwManagers;

import dwEntities.DwCubo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class CuboSession implements CuboSessionLocal {

    @PersistenceContext(unitName = "dw-ejbPU")
    private EntityManager em;

    public void create(DwCubo cubo) {
        em.persist(cubo);
    }

    public void update(DwCubo cubo) {
        em.merge(cubo);
    }
    
    public void remove(DwCubo cubo) {
        em.remove(em.merge(cubo));
    }
    
    public List<DwCubo> obtainAll(){
        return em.createNamedQuery("DwCubo.findAll").getResultList();
    }
    
    public DwCubo obtainById(Integer id){
        return (DwCubo) em.createNamedQuery("DwCubo.findById").
                setParameter("id", id).getSingleResult();
    }
    
    //Obtener maximo id
    public Long getNextIdCampo() {
        return getMaximoId("dw_cubo", "id");
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
