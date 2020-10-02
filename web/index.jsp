<%-- 
    Document   : index
    Created on : 1/out/2020, 16:46:19
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Modelo.Contacto"%>
<%@page import="DAO.ContactoDAO"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CRUD With JSP + Servlet</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
        <meta name="description" content="Crud With JSP + Servlet">
        <meta name="author" content="Beans">
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>
        <div class="container">
            <h1 class="title">CRUD com jsp e servlet</h1>

            <div class="container-1">
                <a href="ContactoServlet?acao=/novo" class="btn-add-contact">Add Contacto</a>
                <div class="search-box">
                    <form action="ContactoServlet?acao=/pesquisar" method="post">
                        <input type="text" placeholder="Pesquisar" name="valor_pesquisa">
                        <select name="tipo_pesquisa">
                            <option value="pk_contacto">ID</option>
                            <option value="nome">Nome</option>
                            <option value="sexo">Sexo</option>
                            <option value="telefone">Telefone</option>
                            <option value="provincia">Provincia</option>
                        </select>
                        <button type="submit" class="btn-submit">Pesquisar</button>
                    </form>                
                </div>
            </div>
            <table>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Nome</th>
                        <th>Sexo</th>
                        <th>Telefone</th>
                        <th>Provincia</th>
                        <th>Ação</th>
                    </tr>
                </thead>
                <%
                    List <Contacto>lista = (List)request.getAttribute("lista");
                    ContactoDAO cd = new ContactoDAO();
                    if(lista == null){
                        lista = cd.listarContacto();
                    }
                    for (Contacto c:lista){
                %>
                <tbody>
                    <tr>
                        <td><%=c.getPk_contacto()%></td>
                        <td><%=c.getNome_contacto()%></td>
                        <td><%=c.getTipo_sexo()%></td>
                        <td><%=c.getTelefone()%></td>
                        <td><%=c.getNomeProvincia()%></td>
                        <td>
                            <a  href="ContactoServlet?acao=/editar&pk_contacto=<%=c.getPk_contacto()%>" class="btn-edit">Editar</a>
                            <a  href="ContactoServlet?acao=/remover&pk_contacto=<%=c.getPk_contacto()%>" class="btn-remove">Remover</a>
                        </td>
                    </tr>
                </tbody>
                <%
                    }
                %>
            </table>
        </div>
    </body>
</html>
