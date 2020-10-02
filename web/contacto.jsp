<%-- 
    Document   : contacto
    Created on : 1/out/2020, 17:54:26
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Modelo.Contacto"%>
<%@page import="DAO.ContactoDAO"%>
<%@page import="java.util.List"%>
<%@page import="DAO.SexoDAO"%>
<%@page import="DAO.ProvinciaDAO"%>
<%@page import="Modelo.Sexo"%>
<%@page import="Modelo.Provincia"%>
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
        <%
            Contacto ct = (Contacto)request.getAttribute("contacto");
        %>
        <div class="container">
            <div class="box-form">
                <div class="box-form-top">
                    <h1><%=(ct != null ) ? "Editar Contacto": "Adcionar Contacto"%></h1>
                </div>
                <form action="ContactoServlet?acao=<%=(ct!=null) ? "/actualizar":"/inserir"%>" method="post">
                    <input type="hidden" name="pk_contacto" value="<%= (ct!=null) ? ct.getPk_contacto():""%>">
                    <div class="form-group">
                        <label for="nome">Nome</label>
                        <input type="text" placeholder="Digite o nome" name="nome" id="nome" value="<%=(ct!=null) ? ct.getNome_contacto():""%>">
                    </div>
                    <div class="form-group">
                        <label for="sexo">Sexo</label>
                        <select name="sexo" id="sexo">
                            <<% if (ct == null){
                            %>
                                <option selected>--Selecione o sexo--</option>
                            <%}%>
                            <%
                                SexoDAO dao= new SexoDAO();
                                List<Sexo> lista = dao.listaDeSexo();
                                for(Sexo s:lista){
                                    if (ct != null && ct.getTipo_sexo().equals(s.getTipo_sexo())){
                            %>
                            <option selected value=<%= s.getPk_sexo() %>> <%=s.getTipo_sexo() %> </option>
                            <%}else{%>
                            <option value=<%= s.getPk_sexo() %>> <%=s.getTipo_sexo() %> </option>
                            <%
                                }}
                            %>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="telefone">Telefone</label>
                        <input type="text" name="telefone" placeholder="Digite o telefone" id="telefone" value="<%=(ct!=null) ? ct.getTelefone():""%>">
                    </div>
                    <div class="form-group">
                        <label for="provincia">Provincia</label>
                        <select name="provincia" id="provincia">
                            <% if(ct==null){ %>
                                <option selected="selected">--Selecione a provincia--</option>
                                <%}%>
                                <% ProvinciaDAO pd= new ProvinciaDAO();
                                   List<Provincia> visualizar = pd.listaProvncia();
                                   for(Provincia p:visualizar){
                                       if (ct != null && ct.getNomeProvincia().equals(p.getNomeProvincia())){
                                   //}
                                %>
                                <option selected="selected" value=<%= p.getPk_provincia() %>> <%=p.getNomeProvincia() %> </option>
                                <%}else%>
                                <option value=<%= p.getPk_provincia() %>> <%=p.getNomeProvincia() %> </option>
                                <%
                                 }
                                %>
                        </select>
                    </div>
                    <button type="submit" class="btn-submit"><%=(ct != null ) ? "Editar": "Adcionar"%></button>
                </form>
            </div>
        </div>
    </body>
</html>
