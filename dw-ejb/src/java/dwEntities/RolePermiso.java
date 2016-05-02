/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dwEntities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario-03
 */
@Entity
@Table(name = "role_permiso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolePermiso.findAll", query = "SELECT r FROM RolePermiso r"),
    @NamedQuery(name = "RolePermiso.findById", query = "SELECT r FROM RolePermiso r WHERE r.id = :id"),
    @NamedQuery(name = "RolePermiso.findByDescripcion", query = "SELECT r FROM RolePermiso r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "RolePermiso.findByPermiso", query = "SELECT r FROM RolePermiso r WHERE r.permiso = :permiso")})
public class RolePermiso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Size(max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "permiso")
    private Integer permiso;
    @JoinColumn(name = "id_role", referencedColumnName = "id")
    @ManyToOne
    private Role idRole;
    @JoinColumn(name = "id_componente", referencedColumnName = "id")
    @ManyToOne
    private ComponenteGrafico idComponente;

    public RolePermiso() {
    }

    public RolePermiso(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPermiso() {
        return permiso;
    }

    public void setPermiso(Integer permiso) {
        this.permiso = permiso;
    }

    public Role getIdRole() {
        return idRole;
    }

    public void setIdRole(Role idRole) {
        this.idRole = idRole;
    }

    public ComponenteGrafico getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(ComponenteGrafico idComponente) {
        this.idComponente = idComponente;
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
        if (!(object instanceof RolePermiso)) {
            return false;
        }
        RolePermiso other = (RolePermiso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dwEntities.RolePermiso[ id=" + id + " ]";
    }
    
}
