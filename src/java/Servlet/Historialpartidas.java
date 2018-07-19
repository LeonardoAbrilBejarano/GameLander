/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Entities.Labpartidas;
import Entities.Labusuarios;
import Services.UsuarioService;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Leonardo
 */
public class Historialpartidas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Historialpartidas</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Historialpartidas at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Accesoadatos3.4.3_v2PU");
        EntityManager em = emf.createEntityManager();
        boolean authcookies = authenticateCookies(cookies, em);
        if (authcookies == true) {
            UsuarioService us = new UsuarioService(em);
            Labusuarios u = us.findUsuario(Integer.parseInt(getUserid(cookies)));
            ArrayList listpartidasuser = us.findUserHistorialPartidasArrayList(u);
            String json = new Gson().toJson(listpartidasuser);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } else {
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    cookies[i].setValue("");
                    cookies[i].setPath("/");
                    cookies[i].setMaxAge(0);
                    response.addCookie(cookies[i]);
                }
            }
        }
        
    }

    public String getUserid(Cookie[] cookies) {
        String userId = null;
        for (Cookie cookie : cookies) {
            if ("user".equals(cookie.getName())) {
                userId = cookie.getValue();
            }
        }
        return userId;
    }
    
    public String getUserpass(Cookie[] cookies) {
        String pass = null;
        for (Cookie cookie : cookies) {
            if ("pass".equals(cookie.getName())) {
                pass = cookie.getValue();
            }
        }
        return pass;
    }

    private boolean authenticateCookies(Cookie[] c, EntityManager em) {
        UsuarioService us = new UsuarioService(em);
        Labusuarios u = us.findUsuario(Integer.parseInt(getUserid(c)));
        if (u.getPass().contentEquals(getUserpass(c))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
