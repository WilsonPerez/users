/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dwManagers;

import dwEntities.Interfaz;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface InterfazSessionLocal {
    public void create(Interfaz cubo);

    public void update(Interfaz cubo);

    public void remove(Interfaz cubo);

    public List<Interfaz> obtainAll();

    public Interfaz obtainById(Integer id);

    public Long getNextIdCampo();
}
