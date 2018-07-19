/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Labpartidas;
import Entities.Labusuarios;
import java.sql.ResultSet;
import java.sql.Timestamp;
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
public class PartidaService {

    EntityManager em;

    public PartidaService(EntityManager em) {
        this.em = em;
    }

    public void removePartida(int id) {
        Labpartidas emp = findPartida(id);
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

    public Labpartidas findPartida(int id) {
        return em.find(Labpartidas.class, id);
    }

    public Collection<Labpartidas> findAllPartidasFromUser(Labusuarios u) {
        Collection<Labpartidas> upaux = this.findAllPartidas();
        Collection<Labpartidas> up = new ArrayList();
        for (Labpartidas p : upaux) {
            if (p.getUserid().getUserid() == u.getUserid()) {
                up.add(p);
            }
        }
        return up;
    }

    public void addPartida(Labusuarios u, Integer id, Double punt, Date in, Date fi) {
        Labpartidas a = new Labpartidas();
        EntityTransaction tx = em.getTransaction();
        a.setUserid(u);
        a.setPuntuacion(punt);
        a.setFechai(in);
        a.setFechaf(fi);
        u.getLabpartidasCollection().add(a);
        try {
            tx.begin();
            em.persist(a);
            em.flush();
            em.refresh(u);
            em.flush();
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public Labpartidas addPartida(Labusuarios u, Double punt, Date in, Date fi) {
        Labpartidas a = new Labpartidas();
        EntityTransaction tx = em.getTransaction();
        a.setUserid(u);
        //a.setPartidaID(id);
        a.setPuntuacion(punt);
        a.setFechai(in);
        a.setFechaf(fi);
        try {
            tx.begin();
            em.persist(a);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return a;
    }

    public void modificarPartida(Labpartidas p) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.flush();
            em.refresh(p);
            em.flush();
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public void printAllPartida() {
        Collection<Labpartidas> al = findAllPartidas();
        for (Object aux : al) {
            Labpartidas p = (Labpartidas) aux;
            System.out.println(p.getUserid().getUserid() + " " + p.getPartidaid() + " " + p.getFechai() + " " + p.getFechaf() + " " + p.getPuntuacion());
        }
    }

    public void printOnePartida(int id) {
        Labpartidas p = this.findPartida(id);
        System.out.println(p.getUserid().getUserid() + " " + p.getPartidaid() + " " + p.getFechai() + " " + p.getFechaf() + " " + p.getPuntuacion());
    }

    @SuppressWarnings("unchecked")
    public Collection<Labpartidas> findAllPartidas() {
        Query query = em.createQuery("SELECT e FROM Labpartidas e");
        return (Collection<Labpartidas>) query.getResultList();
    }

    //select * from partidas order by puntuacion asc limit 10;
    public Collection<Labpartidas> findTop10Partidas() {
        Query query = em.createQuery("select e from Labpartidas e where e.puntuacion is not null order by e.puntuacion asc").setMaxResults(10);
        return (Collection<Labpartidas>) query.getResultList();
    }
    
    public ArrayList findTop10PartidasArrayList() {
        Query query = em.createQuery("select e from Labpartidas e where e.puntuacion is not null order by e.puntuacion asc").setMaxResults(10);
        List<Labpartidas> results = query.getResultList();
        ArrayList arrayj = new ArrayList();
        ArrayList jugador = new ArrayList();
        for (int i = 0; i < results.size(); i++) {
            //User
            Labpartidas p=results.get(i);
            String id = (String) p.getUserid().getUsuario();
            jugador.add(id);
            //Partida
            String idp =  p.getPartidaid().toString();
            jugador.add(idp);            
            //Puntuacion
            String idpp =  p.getPuntuacion().toString();
            jugador.add(idpp);
            //Inicio
            String data = (String) p.getFechai().toString();
            jugador.add(data);
            //Fin
            String data2 = (String) p.getFechaf().toString();
            jugador.add(data2);
            arrayj.add(jugador);
            jugador = new ArrayList();
        }

        return arrayj;
    }

    //SELECT UserID,(SELECT max(fechaf) FROM partidas g WHERE p.userid = g.UserID) FROM `partidas` p GROUP BY UserID
    public ArrayList findOnlinePlayers() {
        Query query = em.createQuery("select e.userid,(select max(g.fechaf) from Labpartidas g where g.userid = e.userid ) from Labpartidas e GROUP BY e.userid");
        List<Object[]> results = query.getResultList();
        ArrayList arrayj = new ArrayList();
        ArrayList jugador = new ArrayList();
        for (int i = 0; i < results.size(); i++) {
            Object[] obj = results.get(i);
            Labusuarios n = (Labusuarios) obj[0];
            String id = (String) n.getUsuario();
            jugador.add(id);
            Timestamp p = (Timestamp) obj[1];
            String data = (String) p.toString();
            jugador.add(data);
            arrayj.add(jugador);
            jugador = new ArrayList();
        }
        return arrayj;
    }
}
