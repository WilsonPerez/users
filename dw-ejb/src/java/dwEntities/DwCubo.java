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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author root
 */
@Entity
@Table(name = "dw_cubo", catalog = "dw_siim", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DwCubo.findAll", query = "SELECT d FROM DwCubo d"),
    @NamedQuery(name = "DwCubo.findById", query = "SELECT d FROM DwCubo d WHERE d.id = :id"),
    @NamedQuery(name = "DwCubo.findByPrioridad", query = "SELECT d FROM DwCubo d WHERE d.prioridad = :prioridad")})
public class DwCubo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prioridad", nullable = false)
    private int prioridad;
    @JoinTable(name = "dw_cubo_tabla", joinColumns = {
        @JoinColumn(name = "id_dw_cubo", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "id_dw_tabla", referencedColumnName = "id", nullable = false)})
    @ManyToMany
    private List<DwTabla> dwTablaList;
    @JoinColumn(name = "id_dw_modulo", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DwModulo idDwModulo;

    public DwCubo() {
    }

    public DwCubo(Long id) {
        this.id = id;
    }

    public DwCubo(Long id, int prioridad) {
        this.id = id;
        this.prioridad = prioridad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    @XmlTransient
    public List<DwTabla> getDwTablaList() {
        return dwTablaList;
    }

    public void setDwTablaList(List<DwTabla> dwTablaList) {
        this.dwTablaList = dwTablaList;
    }

    public DwModulo getIdDwModulo() {
        return idDwModulo;
    }

    public void setIdDwModulo(DwModulo idDwModulo) {
        this.idDwModulo = idDwModulo;
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
        if (!(object instanceof DwCubo)) {
            return false;
        }
        DwCubo other = (DwCubo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dwEntities.DwCubo[ id=" + id + " ]";
    }
    
}
