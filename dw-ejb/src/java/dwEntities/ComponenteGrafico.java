/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dwEntities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author Usuario-03
 */
@Entity
@Table(name = "componente_grafico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComponenteGrafico.findAll", query = "SELECT c FROM ComponenteGrafico c"),
    @NamedQuery(name = "ComponenteGrafico.findById", query = "SELECT c FROM ComponenteGrafico c WHERE c.id = :id"),
    @NamedQuery(name = "ComponenteGrafico.findByNombre", query = "SELECT c FROM ComponenteGrafico c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "ComponenteGrafico.findByDescripcion", query = "SELECT c FROM ComponenteGrafico c WHERE c.descripcion = :descripcion")})
public class ComponenteGrafico implements Serializable {

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
    @JoinColumn(name = "id_interfaz", referencedColumnName = "id")
    @ManyToOne
    private Interfaz idInterfaz;
    @OneToMany(mappedBy = "idComponente")
    private Collection<RolePermiso> rolePermisoCollection;

    public ComponenteGrafico() {
    }

    public ComponenteGrafico(Long id) {
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

    public Interfaz getIdInterfaz() {
        return idInterfaz;
    }

    public void setIdInterfaz(Interfaz idInterfaz) {
        this.idInterfaz = idInterfaz;
    }

    @XmlTransient
    public Collection<RolePermiso> getRolePermisoCollection() {
        return rolePermisoCollection;
    }

    public void setRolePermisoCollection(Collection<RolePermiso> rolePermisoCollection) {
        this.rolePermisoCollection = rolePermisoCollection;
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
        if (!(object instanceof ComponenteGrafico)) {
            return false;
        }
        ComponenteGrafico other = (ComponenteGrafico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dwEntities.ComponenteGrafico[ id=" + id + " ]";
    }
    
}
