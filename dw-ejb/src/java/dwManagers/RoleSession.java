/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dwManagers;

import dwEntities.Role;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class RoleSession implements RoleSessionLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
     @PersistenceContext(unitName = "dw-ejbPU")
    private EntityManager em;

    public void create(Role modulo) {
        em.persist(modulo);
    }

    public void update(Role modulo) {
        em.merge(modulo);
    }
    
    public void remove(Role modulo) {
        em.remove(em.merge(modulo));
    }
    
    public List<Role> obtainAll(){
        return em.createNamedQuery("Role.findAll").getResultList();
    }
    
    public Role obtainById(Integer id){
        return (Role) em.createNamedQuery("Role.findById").
                setParameter("id", id).getSingleResult();
    }
    
    //Obtener maximo id
    public Long getNextIdCampo() {
        return getMaximoId("role", "id");
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
