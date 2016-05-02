/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dwManagers;

import dwEntities.Usuario;
import dwEntities.UsuarioRole;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface UsuarioSessionLocal {
    
     public void create(Usuario modulo);

    public void update(Usuario modulo);

    public void remove(Usuario modulo);

    public List<Usuario> obtainAll();

    public Usuario obtainById(Integer id);

    public Long getNextIdCampo();
    
    public List<UsuarioRole> validateUser(String name,String password);

}
