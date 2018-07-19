/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Leonardo
 */
@Entity
@Table(name = "labpartidas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Labpartidas.findAll", query = "SELECT l FROM Labpartidas l"),
    @NamedQuery(name = "Labpartidas.findByPartidaid", query = "SELECT l FROM Labpartidas l WHERE l.partidaid = :partidaid"),
    @NamedQuery(name = "Labpartidas.findByFechai", query = "SELECT l FROM Labpartidas l WHERE l.fechai = :fechai"),
    @NamedQuery(name = "Labpartidas.findByFechaf", query = "SELECT l FROM Labpartidas l WHERE l.fechaf = :fechaf"),
    @NamedQuery(name = "Labpartidas.findByPuntuacion", query = "SELECT l FROM Labpartidas l WHERE l.puntuacion = :puntuacion")})
public class Labpartidas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "partidaid")
    private Integer partidaid;
    @Column(name = "fechai")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechai;
    @Column(name = "fechaf")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaf;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "puntuacion")
    private Double puntuacion;
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    @ManyToOne
    private Labusuarios userid;

    public Labpartidas() {
    }

    public Labpartidas(Integer partidaid) {
        this.partidaid = partidaid;
    }

    public Integer getPartidaid() {
        return partidaid;
    }

    public void setPartidaid(Integer partidaid) {
        this.partidaid = partidaid;
    }

    public Date getFechai() {
        return fechai;
    }

    public void setFechai(Date fechai) {
        this.fechai = fechai;
    }

    public Date getFechaf() {
        return fechaf;
    }

    public void setFechaf(Date fechaf) {
        this.fechaf = fechaf;
    }

    public Double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Labusuarios getUserid() {
        return userid;
    }

    public void setUserid(Labusuarios userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (partidaid != null ? partidaid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Labpartidas)) {
            return false;
        }
        Labpartidas other = (Labpartidas) object;
        if ((this.partidaid == null && other.partidaid != null) || (this.partidaid != null && !this.partidaid.equals(other.partidaid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Labpartidas[ partidaid=" + partidaid + " ]";
    }
    
}
