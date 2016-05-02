/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dwManagers;

import dwEntities.DwCubo;
import dwEntities.DwModulo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface ModuloSessionLocal {

    public void create(DwModulo modulo);

    public void update(DwModulo modulo);

    public void remove(DwModulo modulo);

    public List<DwModulo> obtainAll();

    public DwModulo obtainById(Integer id);

    public Long getNextIdCampo();

       
    
}
