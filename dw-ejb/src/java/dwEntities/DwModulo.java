/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dwEntities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "dw_modulo", catalog = "dw_siim", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DwModulo.findAll", query = "SELECT d FROM DwModulo d"),
    @NamedQuery(name = "DwModulo.findById", query = "SELECT d FROM DwModulo d WHERE d.id = :id"),
    @NamedQuery(name = "DwModulo.findByNombre", query = "SELECT d FROM DwModulo d WHERE d.nombre = :nombre")})
public class DwModulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDwModulo")
    private List<DwCubo> dwCuboList;

    public DwModulo() {
    }

    public DwModulo(Long idModulo) {
        this.id = idModulo;
    }

    public DwModulo(Long idModulo, String nombre) {
        this.id = idModulo;
        this.nombre = nombre;
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

    @XmlTransient
    public List<DwCubo> getDwCuboList() {
        return dwCuboList;
    }

    public void setDwCuboList(List<DwCubo> dwCuboList) {
        this.dwCuboList = dwCuboList;
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
        if (!(object instanceof DwModulo)) {
            return false;
        }
        DwModulo other = (DwModulo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dwEntities.DwModulo[ id=" + id + " ]";
    }
    
}
