/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Leonardo
 */
@Entity
@Table(name = "labusuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Labusuarios.findAll", query = "SELECT l FROM Labusuarios l"),
    @NamedQuery(name = "Labusuarios.findByUserid", query = "SELECT l FROM Labusuarios l WHERE l.userid = :userid"),
    @NamedQuery(name = "Labusuarios.findByUsuario", query = "SELECT l FROM Labusuarios l WHERE l.usuario = :usuario"),
    @NamedQuery(name = "Labusuarios.findByPass", query = "SELECT l FROM Labusuarios l WHERE l.pass = :pass"),
    @NamedQuery(name = "Labusuarios.findByEmail", query = "SELECT l FROM Labusuarios l WHERE l.email = :email")})
public class Labusuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "userid")
    private Integer userid;
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "pass")
    private String pass;
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "userid")
    private Collection<Labpartidas> labpartidasCollection;

    public Labusuarios() {
    }

    public Labusuarios(Integer userid) {
        this.userid = userid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public Collection<Labpartidas> getLabpartidasCollection() {
        return labpartidasCollection;
    }

    public void setLabpartidasCollection(Collection<Labpartidas> labpartidasCollection) {
        this.labpartidasCollection = labpartidasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Labusuarios)) {
            return false;
        }
        Labusuarios other = (Labusuarios) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Labusuarios[ userid=" + userid + " ]";
    }
    
}
