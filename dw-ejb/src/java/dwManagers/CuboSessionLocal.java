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
public interface CuboSessionLocal {

    public void create(DwCubo cubo);

    public void update(DwCubo cubo);

    public void remove(DwCubo cubo);

    public List<DwCubo> obtainAll();

    public DwCubo obtainById(Integer id);

    public Long getNextIdCampo();

    
    
}
