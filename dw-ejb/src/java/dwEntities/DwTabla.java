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
import javax.persistence.ManyToMany;
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
@Table(name = "dw_tabla", catalog = "dw_siim", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DwTabla.findAll", query = "SELECT d FROM DwTabla d"),
    @NamedQuery(name = "DwTabla.findById", query = "SELECT d FROM DwTabla d WHERE d.id = :id"),
    @NamedQuery(name = "DwTabla.findByNombre", query = "SELECT d FROM DwTabla d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DwTabla.findByTipoTabla", query = "SELECT d FROM DwTabla d WHERE d.tipoTabla = :tipoTabla"),
    @NamedQuery(name = "DwTabla.findByCompartida", query = "SELECT d FROM DwTabla d WHERE d.compartida = :compartida"),
    @NamedQuery(name = "DwTabla.findByPrioridad", query = "SELECT d FROM DwTabla d WHERE d.prioridad = :prioridad"),
    @NamedQuery(name = "DwTabla.findByAccionDimension", query = "SELECT d FROM DwTabla d WHERE d.accionDimension = :accionDimension")})
public class DwTabla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_tabla", nullable = false)
    private int tipoTabla;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compartida", nullable = false)
    private int compartida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prioridad", nullable = false)
    private int prioridad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "accion_dimension", nullable = false, length = 20)
    private String accionDimension;
    @ManyToMany(mappedBy = "dwTablaList")
    private List<DwCubo> dwCuboList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDwTabla")
    private List<DwCampo> dwCampoList;

    public DwTabla() {
    }

    public DwTabla(Long id) {
        this.id = id;
    }

    public DwTabla(Long id, String nombre, int tipoTabla, int compartida, int prioridad, String accionDimension) {
        this.id = id;
        this.nombre = nombre;
        this.tipoTabla = tipoTabla;
        this.compartida = compartida;
        this.prioridad = prioridad;
        this.accionDimension = accionDimension;
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

    public int getTipoTabla() {
        return tipoTabla;
    }

    public void setTipoTabla(int tipoTabla) {
        this.tipoTabla = tipoTabla;
    }

    public int getCompartida() {
        return compartida;
    }

    public void setCompartida(int compartida) {
        this.compartida = compartida;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getAccionDimension() {
        return accionDimension;
    }

    public void setAccionDimension(String accionDimension) {
        this.accionDimension = accionDimension;
    }

    @XmlTransient
    public List<DwCubo> getDwCuboList() {
        return dwCuboList;
    }

    public void setDwCuboList(List<DwCubo> dwCuboList) {
        this.dwCuboList = dwCuboList;
    }

    @XmlTransient
    public List<DwCampo> getDwCampoList() {
        return dwCampoList;
    }

    public void setDwCampoList(List<DwCampo> dwCampoList) {
        this.dwCampoList = dwCampoList;
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
        if (!(object instanceof DwTabla)) {
            return false;
        }
        DwTabla other = (DwTabla) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dwEntities.DwTabla[ id=" + id + " ]";
    }
    
}
