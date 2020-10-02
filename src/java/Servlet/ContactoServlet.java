/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Modelo.Contacto;
import DAO.ContactoDAO;


import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author LENOVO
 */
public class ContactoServlet extends HttpServlet {
    
    private ContactoDAO contactoDAO; 
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void init(){
        contactoDAO = new ContactoDAO();
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
        //processRequest(request, response);
//        String acao = request.getServletPath();
        String acao = request.getParameter("acao");
        try{
            switch(acao){
                case "/novo":
                    mostrarNovoContactoForm(request, response);
                    break;
                case "/inserir":
                    inserirContacto(request, response);
                    break;
                case "/remover":
                    deletarContacto(request, response);
                    break;
                case "/editar":
                    mostrarEditContactoForm(request, response);
                    break;
                case "/actualizar":
                    actualizarContacto(request, response);
                    break;
                case "/pesquisar":
                    pesquisar(request, response);
                    break;
                default:
                    break;
            }
        }catch(SQLException ex){
            throw new ServletException(ex);
        }
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
        //processRequest(request, response);
        doGet(request, response);
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
    
    
    /**
     * Todos os métodos
     * /
     */
    
    public static boolean isNumeric(String strNum){
        if(strNum == null){
            return false;
        }
        try{
            int d = Integer.parseInt(strNum);
        } catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }
    
    
    
    private void buscaGenerica(HttpServletRequest request, HttpServletResponse response ,String value, String tipo_pesquisa)
            throws SQLException, IOException, ServletException{
        if(tipo_pesquisa.equals("pk_contacto") && isNumeric(value))
        {
            List lista = contactoDAO.buscaID(Integer.parseInt(value));
            request.setAttribute("lista", lista);
        }else if(tipo_pesquisa.equals("nome") && !value.isEmpty())
        {
            List <Contacto>lista = contactoDAO.buscaNome(value);
            request.setAttribute("lista", lista);
        }else if(tipo_pesquisa.equals("sexo") && !value.isEmpty())
        {
            List <Contacto>lista = contactoDAO.buscaSexo(value);
            request.setAttribute("lista", lista);
        }else if(tipo_pesquisa.equals("telefone") && !value.isEmpty())
        {
            List <Contacto>lista = contactoDAO.buscaTelefone(value);
            request.setAttribute("lista", lista);
        }else if(tipo_pesquisa.equals("provincia"))
        {
            List <Contacto>lista = contactoDAO.buscaProvincia(value);
            request.setAttribute("lista", lista);
        }else{
            List <Contacto>lista = contactoDAO.listarContacto();
            request.setAttribute("lista", lista);
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
    
    
    private void pesquisar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        try{
            String tipo_pesquisa = request.getParameter("tipo_pesquisa");
            String valor_pesquisa = request.getParameter("valor_pesquisa");
            switch(tipo_pesquisa){
                case "pk_contacto":
                    buscaGenerica(request,response,valor_pesquisa, tipo_pesquisa);
                    break;
                case "nome":
                    buscaGenerica(request,response,valor_pesquisa, tipo_pesquisa);
                    break;
                case "sexo":
                    buscaGenerica(request,response,valor_pesquisa, tipo_pesquisa);
                    break;
                case "telefone":
                    buscaGenerica(request,response,valor_pesquisa, tipo_pesquisa);
                    break;
                case "provincia":
                    buscaGenerica(request,response,valor_pesquisa, tipo_pesquisa);
                    break;
                default:
                    buscaGenerica(request,response,"", "");
                    break;
            }
        }catch(SQLException e){
            throw new ServletException(e);
        }
        
    }
    
    private void listarContacto(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException, ServletException{
        List <Contacto> listaContacto = contactoDAO.listarContacto();
        request.setAttribute("listaContacto", listaContacto);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
    
    private void mostrarNovoContactoForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("contacto.jsp");
        dispatcher.forward(request, response);
    }
    
    private void mostrarEditContactoForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        int pk_contacto = Integer.parseInt(request.getParameter("pk_contacto"));
        Contacto contactoExistente = contactoDAO.buscaId(pk_contacto);
        request.setAttribute("contacto", contactoExistente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("contacto.jsp");
        dispatcher.forward(request, response);
    }
    
    private void inserirContacto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nome = request.getParameter("nome");
        int sexo = Integer.parseInt(request.getParameter("sexo"));
        String telefone = request.getParameter("telefone");
        int provincia = Integer.parseInt(request.getParameter("provincia"));
        
        
        
        if(nome != null && telefone != null){
            if(Contacto.isValid(nome, telefone)) {
                Contacto contacto = new Contacto(nome, sexo, telefone, provincia);
                contactoDAO.cadastroContacto(contacto);
            }
        }
        response.sendRedirect("index.jsp");
    }
    
    private void actualizarContacto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int pk_contacto = Integer.parseInt(request.getParameter("pk_contacto"));
        String nome = request.getParameter("nome");
        int sexo = Integer.parseInt(request.getParameter("sexo"));
        String telefone = request.getParameter("telefone");
        int provincia = Integer.parseInt(request.getParameter("provincia"));
        
        Contacto contacto = new Contacto(pk_contacto,nome, sexo, telefone, provincia);
        contactoDAO.actualizar(contacto);
        response.sendRedirect("index.jsp");
    }
    
    private void deletarContacto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("pk_contacto"));
        contactoDAO.elimanarContactos(id);
        response.sendRedirect("index.jsp");
    }
}   
