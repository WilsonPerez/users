/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dwEntities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author root
 */
@Entity
@Table(name = "interfaz")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Interfaz.findAll", query = "SELECT i FROM Interfaz i"),
    @NamedQuery(name = "Interfaz.findById", query = "SELECT i FROM Interfaz i WHERE i.id = :id"),
    @NamedQuery(name = "Interfaz.findByNombre", query = "SELECT i FROM Interfaz i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "Interfaz.findByDescripcion", query = "SELECT i FROM Interfaz i WHERE i.descripcion = :descripcion")})
public class Interfaz implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Size(max = 250)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "idInterfaz")
    private List<ComponenteGrafico> componenteGraficoList;

    public Interfaz() {
    }

    public Interfaz(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<ComponenteGrafico> getComponenteGraficoList() {
        return componenteGraficoList;
    }

    public void setComponenteGraficoList(List<ComponenteGrafico> componenteGraficoList) {
        this.componenteGraficoList = componenteGraficoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Interfaz)) {
            return false;
        }
        Interfaz other = (Interfaz) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dwEntities.Interfaz[ id=" + id + " ]";
    }
    
}
