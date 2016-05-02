/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dwManagers;

import dwEntities.Role;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface RoleSessionLocal {
     public void create(Role modulo);

    public void update(Role modulo);

    public void remove(Role modulo);

    public List<Role> obtainAll();

    public Role obtainById(Integer id);

    public Long getNextIdCampo();
}
