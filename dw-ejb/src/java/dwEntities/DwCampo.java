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
 * @author root
 */
@Entity
@Table(name = "dw_campo", catalog = "dw_siim", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DwCampo.findAll", query = "SELECT d FROM DwCampo d"),
    @NamedQuery(name = "DwCampo.findById", query = "SELECT d FROM DwCampo d WHERE d.id = :id"),
    @NamedQuery(name = "DwCampo.findByNombre", query = "SELECT d FROM DwCampo d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DwCampo.findByTipoDato", query = "SELECT d FROM DwCampo d WHERE d.tipoDato = :tipoDato"),
    @NamedQuery(name = "DwCampo.findByAccionCampo", query = "SELECT d FROM DwCampo d WHERE d.accionCampo = :accionCampo")})
public class DwCampo implements Serializable {

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
    @Size(min = 1, max = 100)
    @Column(name = "tipo_dato", nullable = false, length = 100)
    private String tipoDato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "accion_campo", nullable = false, length = 20)
    private String accionCampo;
    @JoinColumn(name = "id_dw_tabla", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DwTabla idDwTabla;

    public DwCampo() {
    }

    public DwCampo(Long id) {
        this.id = id;
    }

    public DwCampo(Long id, String nombre, String tipoDato, String accionCampo) {
        this.id = id;
        this.nombre = nombre;
        this.tipoDato = tipoDato;
        this.accionCampo = accionCampo;
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

    public String getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    public String getAccionCampo() {
        return accionCampo;
    }

    public void setAccionCampo(String accionCampo) {
        this.accionCampo = accionCampo;
    }

    public DwTabla getIdDwTabla() {
        return idDwTabla;
    }

    public void setIdDwTabla(DwTabla idDwTabla) {
        this.idDwTabla = idDwTabla;
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
        if (!(object instanceof DwCampo)) {
            return false;
        }
        DwCampo other = (DwCampo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dwEntities.DwCampo[ id=" + id + " ]";
    }
    
}
