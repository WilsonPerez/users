/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dwManagers;

import dwEntities.ComponenteGrafico;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface ComponenteGraficoSessionLocal {
    public void create(ComponenteGrafico cubo);

    public void update(ComponenteGrafico cubo);

    public void remove(ComponenteGrafico cubo);

    public List<ComponenteGrafico> obtainAll();

    public ComponenteGrafico obtainById(Integer id);

    public Long getNextIdCampo();
}
