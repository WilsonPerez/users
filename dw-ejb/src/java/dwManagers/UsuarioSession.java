/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dwManagers;

import dwEntities.Role;
import dwEntities.Usuario;
import dwEntities.UsuarioRole;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class UsuarioSession implements UsuarioSessionLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
     @PersistenceContext(unitName = "dw-ejbPU")
    private EntityManager em;

    public void create(Usuario modulo) {
        em.persist(modulo);
    }

    public void update(Usuario modulo) {
        em.merge(modulo);
    }
    
    public void remove(Usuario modulo) {
        em.remove(em.merge(modulo));
    }
    
    public List<Usuario> obtainAll(){
        return em.createNamedQuery("Usuario.findAll").getResultList();
    }
    
    public Usuario obtainById(Integer id){
        return (Usuario) em.createNamedQuery("Usuario.findById").
                setParameter("id", id).getSingleResult();
    }
    
    //Obtener maximo id
    public Long getNextIdCampo() {
        return getMaximoId("usuario", "id");
    }
    
    private Long getMaximoId(String iTabla, String iIdentificador) {
        Long aux = (Long) em.createNativeQuery("SELECT MAX(" + iIdentificador + ") "
                + "FROM " + iTabla).getSingleResult();

        if(aux == null){
            return Long.valueOf("1");
        }
        return aux + 1;

    }
    public List<UsuarioRole> validateUser(String name,String password) {
        List<Usuario> findAll=em.createNamedQuery("Usuario.findAll").getResultList();
        List<UsuarioRole> userRolesAux=new ArrayList<UsuarioRole>();
        for(int i=0; i<findAll.size(); i++){
            if(name.compareTo(findAll.get(i).getUsername())==0)
                if(password.compareTo(findAll.get(i).getPassword())==0){
                    List<UsuarioRole> userRoles=(List<UsuarioRole>) findAll.get(i).getUsuarioRoleCollection();
                    return userRoles;
                }
        }
        return userRolesAux;
    }
}
