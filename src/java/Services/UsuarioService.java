/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Labpartidas;
import Entities.Labusuarios;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author Leonardo
 */
public class UsuarioService {

    EntityManager em;

    public UsuarioService(EntityManager em) {
        this.em = em;
    }

    public void flushem() {
        em.flush();
    }

    public void removeUsuario(int id) {
        Labusuarios emp = findUsuario(id);
        EntityTransaction tx = em.getTransaction();
        if (emp != null) {
            try {
                tx.begin();
                em.remove(emp);
                tx.commit();
            } catch (Exception e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    public Labusuarios findUsuario(int id) {
        return em.find(Labusuarios.class, id);
    }

    public boolean[] findUsuario(String name, String pass) {
        Collection<Labusuarios> u = findAllUsuarios();
        boolean[] ub = new boolean[2];
        ub[0] = false;
        ub[1] = false;
        for (Labusuarios uaux : u) {
            if (uaux.getUsuario().contentEquals(name)) {
                ub[0] = true;
                if (uaux.getPass().contentEquals(pass)) {
                    ub[1] = true;
                    break;
                } else {
                    ub[1] = false;
                    break;
                }
            }
        }
        return ub;
    }

    public int findidUsuario(String name, String pass) {
        Collection<Labusuarios> u = findAllUsuarios();
        int id = -1;
        for (Labusuarios uaux : u) {
            if (uaux.getUsuario().contentEquals(name)) {
                if (uaux.getPass().contentEquals(pass)) {
                    id = uaux.getUserid();
                    break;
                }
            }
        }
        return id;
    }

    public boolean[] findUsuario(String name, String pass, String email) {
        Collection<Labusuarios> u = findAllUsuarios();
        boolean[] ub = new boolean[2];
        ub[0] = false;
        ub[1] = false;
        for (Labusuarios uaux : u) {
            if (uaux.getUsuario().contentEquals(name) || uaux.getEmail().contentEquals(email)) {
                if (uaux.getUsuario().contentEquals(name)) {
                    ub[0] = true;
                    break;
                }
                if (uaux.getEmail().contentEquals(email)) {
                    ub[1] = true;
                    break;
                }
            }
        }
        return ub;
    }

    public boolean addUsuario(String usuario, String pass, String email, Collection<Labpartidas> pu) throws FileNotFoundException {
        Labusuarios a = new Labusuarios();
        EntityTransaction tx = em.getTransaction();
        a.setUsuario(usuario);
        a.setPass(pass);
        a.setEmail(email);
        a.setLabpartidasCollection(pu);
        try {
            tx.begin();
            em.persist(a);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            File f = new File("Calificaciones.txt");
            PrintWriter fichero = new PrintWriter("Calificaciones.txt");
            e.printStackTrace();
            e.printStackTrace(fichero);
            return false;
        }
    }

    private Collection<Labpartidas> generatePartidasUser(Labusuarios a) {
        PartidaService ps = new PartidaService(em);
        return ps.findAllPartidasFromUser(a);
    }

    public void printAllUsuario() {
        Collection<Labusuarios> al = findAllUsuarios();
        for (Object aux : al) {
            Labusuarios p = (Labusuarios) aux;
            System.out.println(p.getUserid() + " " + p.getUsuario() + " " + p.getPass() + " " + p.getEmail() + " ");
            Collection<Labpartidas> pu = p.getLabpartidasCollection();
            for (Labpartidas paux : pu) {
                System.out.println(paux.getUserid().getUserid() + " " + paux.getPartidaid() + " " + paux.getFechai() + " " + paux.getFechaf() + " " + paux.getPuntuacion());
            }
        }
    }

    public void printOneUsuario(int id) {
        Labusuarios p = this.findUsuario(id);
        System.out.println(p.getUserid() + " " + p.getUsuario() + " " + p.getPass() + " " + p.getEmail() + " ");
        Collection<Labpartidas> pu = p.getLabpartidasCollection();
        for (Labpartidas paux : pu) {
            System.out.println(paux.getUserid().getUserid() + " " + paux.getPartidaid() + " " + paux.getFechai() + " " + paux.getFechaf() + " " + paux.getPuntuacion());
        }

    }

    @SuppressWarnings("unchecked")
    public Collection<Labusuarios> findAllUsuarios() {
        Query query = em.createQuery("SELECT e FROM Labusuarios e");
        return (Collection<Labusuarios>) query.getResultList();
    }

    public Collection<Labpartidas> findUserHistorialPartidas(Labusuarios u) {
        Query query = em.createQuery("select e from Labpartidas e where e.userid = :iduser ").setParameter("iduser", u);
        return (Collection<Labpartidas>) query.getResultList();
    }

     public ArrayList findUserHistorialPartidasArrayList(Labusuarios u) {
        Query query = em.createQuery("select e from Labpartidas e where e.userid = :iduser ").setParameter("iduser", u);
        List<Labpartidas> results = query.getResultList();
        ArrayList arrayj = new ArrayList();
        ArrayList jugador = new ArrayList();
        for (int i = 0; i < results.size(); i++) {
            //User
            Labpartidas p = results.get(i);
            String id = (String) p.getUserid().getUserid().toString();
            jugador.add(id);
            //Partida
            String idp = p.getPartidaid().toString();
            jugador.add(idp);
            //Puntuacion
            String idpp ="Default";
            if (p.getPuntuacion() == null) {
                idpp= "No tiene ya que el jugador abandono la partida";
            } else {
                idpp = p.getPuntuacion().toString();
            }
            jugador.add(idpp);
            //Inicio
            String data = (String) p.getFechai().toString();
            jugador.add(data);
            //Fin
            String data2 ="Default";
            if (p.getFechaf() == null) {
                data2= "No tiene ya que el jugador abandono la partida";
            } else {
                data2 = p.getFechaf().toString();
            }
            jugador.add(data2);
            arrayj.add(jugador);
            jugador = new ArrayList();
        }
        return arrayj;
    }

}
