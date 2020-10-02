/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.Conection;
import Modelo.Contacto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author LENOVO
 */
public class ContactoDAO {
    private final String inserir_contacto="insert into contacto(nome_contacto,fk_sexo,telefone,fk_provincia)values(?,?,?,?)";
    
    private final String actualizar_contacto = "update contacto set nome_contacto = ?, fk_sexo = ?, telefone = ?, fk_provincia=? where pk_contacto = ?";
    
    private final String listarContacto="select ct.pk_contacto,ct.nome_contacto, ct.telefone,s.descricao as desc_sexo,pr.descricao as desc_provincia from contacto ct,"
                                        + " sexo s, provincia pr where ct.fk_sexo= s.pk_sexo and ct.fk_provincia=pr.pk_provincia  order by ct.nome_contacto";
    
    private final String eliminar_contacto="delete from contacto where pk_contacto=?";
    
    private final String buscaPor_id="select ct.pk_contacto,ct.nome_contacto,s.descricao as desc_sexo,\n" +
    "ct.telefone,pv.descricao as desc_provincia from contacto ct,\n" +
    "sexo s,provincia pv where ct.fk_sexo=s.pk_sexo and ct.fk_provincia=pv.pk_provincia \n" +
    "and ct.pk_contacto = ? order by pk_contacto asc";

    private final String buscaPor_nome = "select ct.pk_contacto,ct.nome_contacto,s.descricao as desc_sexo,\n" +
    "ct.telefone,pv.descricao as desc_provincia from contacto ct,\n" +
    "sexo s,provincia pv where ct.fk_sexo=s.pk_sexo and ct.fk_provincia=pv.pk_provincia \n" +
    "and ct.nome_contacto = ? order by pk_contacto asc";
    
    private final String buscaPor_telefone = "select ct.pk_contacto,ct.nome_contacto,s.descricao as desc_sexo,\n" +
    "ct.telefone,pv.descricao as desc_provincia from contacto ct,\n" +
    "sexo s,provincia pv where ct.fk_sexo=s.pk_sexo and ct.fk_provincia=pv.pk_provincia \n" +
    "and ct.telefone = ? order by pk_contacto asc";
    
    private final String buscaPor_sexo = "select ct.pk_contacto,ct.nome_contacto,s.descricao as desc_sexo,\n" +
    "ct.telefone,pv.descricao as desc_provincia from contacto ct,\n" +
    "sexo s,provincia pv where ct.fk_sexo=s.pk_sexo and ct.fk_provincia=pv.pk_provincia \n" +
    "and s.descricao = ? order by pk_contacto asc";
    
    private final String buscaPor_provincia = "select ct.pk_contacto,ct.nome_contacto,s.descricao as desc_sexo,\n" +
    "ct.telefone,pv.descricao as desc_provincia from contacto ct,\n" +
    "sexo s,provincia pv where ct.fk_sexo=s.pk_sexo and ct.fk_provincia=pv.pk_provincia \n" +
    "and pv.descricao = ? order by pk_contacto asc";
    
    Connection con;
    PreparedStatement pstm;
    
    public void cadastroContacto(Contacto c)
    {
        try
        {
            con= Conection.ligarBD();
            pstm=con.prepareStatement(inserir_contacto);
            pstm.setString(1, c.getNome_contacto());
            pstm.setInt(2, c.getFk_sexo());
            pstm.setString(3, c.getTelefone());
            pstm.setInt(4, c.getFk_provincia());
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso");
        }
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null,"Erro ao inserir os dados" + sqle.getMessage());
        }
        finally
        {
            Conection.closeConection(con, pstm);
        }
    }
    
    
    public void actualizar(Contacto c){
        try
        {
            con= Conection.ligarBD();
            pstm=con.prepareStatement(actualizar_contacto);
            pstm.setString(1, c.getNome_contacto());
            pstm.setInt(2, c.getFk_sexo());
            pstm.setString(3, c.getTelefone());
            pstm.setInt(4, c.getFk_provincia());
            pstm.setInt(5, c.getPk_contacto());
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Dados Actualizado com sucesso!");
        }catch(SQLException sql){
            JOptionPane.showMessageDialog(null, "ERRO: "+sql.getMessage());
            sql.printStackTrace();
        }
    }
    public void elimanarContactos(Contacto c)
    {
        try
        {
            con= Conection.ligarBD();
            pstm=con.prepareStatement(eliminar_contacto);
            pstm.setInt(1, c.getPk_contacto());
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(null, "contacto eliminado com sucesso");
        }
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null,"Erro ao eliminar");
        }
        finally
        {
            Conection.closeConection(con, pstm);
        }
    }
    
    public void elimanarContactos(int id)
    {
        try
        {
            con= Conection.ligarBD();
            pstm=con.prepareStatement(eliminar_contacto);
            pstm.setInt(1, id);
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(null, "contacto eliminado com sucesso");
        }
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null,"Erro ao eliminar");
        }
        finally
        {
            Conection.closeConection(con, pstm);
        }
    }
    
    public List<Contacto> listarContacto()
    {
        List<Contacto> lista= new ArrayList();
        ResultSet rs=null;
        try
        {
            con= Conection.ligarBD();
            pstm= con.prepareStatement(listarContacto);
            rs= pstm.executeQuery();
            while(rs.next())
            {
                Contacto c= new Contacto();
                c.setPk_contacto(rs.getInt("pk_contacto"));
                c.setNome_contacto(rs.getString("nome_contacto"));
                c.setTipo_sexo(rs.getString("desc_sexo"));
                c.setTelefone(rs.getString("telefone"));
                c.setNomeProvincia(rs.getString("desc_provincia"));
                lista.add(c);
            }
            
        }
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null,"Erro ao listar os contactos" + sqle.getMessage());
        }
        finally
        {
            Conection.closeConection(con, pstm, rs);
        }
        return lista;
    }
    
    public Contacto buscaId( Contacto c)
    {
        ResultSet rs=null;
        try
        {
            con= Conection.ligarBD();
            pstm= con.prepareStatement(buscaPor_id);
            pstm.setInt(1, c.getPk_contacto());
            rs= pstm.executeQuery();
            while(rs.next())
            {
                Contacto ct= new Contacto();
                ct.setPk_contacto(rs.getInt("pk_contacto"));
                ct.setNome_contacto(rs.getString("nome_contacto"));
                ct.setTipo_sexo(rs.getString("desc_sexo"));
                ct.setTelefone(rs.getString("telefone"));
                ct.setNomeProvincia(rs.getString("desc_provincia"));
                return ct;
            }
        }
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null,"Erro na busca do id"+ sqle.getMessage());
        }
        finally
        {
            Conection.closeConection(con, pstm, rs);
        }
        return null;
    }
    
    public Contacto buscaId( int id)
    {
        ResultSet rs=null;
        try
        {
            con= Conection.ligarBD();
            pstm= con.prepareStatement(buscaPor_id);
            pstm.setInt(1, id);
            rs= pstm.executeQuery();
            while(rs.next())
            {
                Contacto ct= new Contacto();
                ct.setPk_contacto(rs.getInt("pk_contacto"));
                ct.setNome_contacto(rs.getString("nome_contacto"));
                ct.setTipo_sexo(rs.getString("desc_sexo"));
                ct.setTelefone(rs.getString("telefone"));
                ct.setNomeProvincia(rs.getString("desc_provincia"));
                return ct;
            }
        }
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null,"Erro na busca do id"+ sqle.getMessage());
        }
        finally
        {
            Conection.closeConection(con, pstm, rs);
        }
        return null;
    }
    
   
    public List<Contacto> buscaNome(String nome)
    {
        List<Contacto> lista= new ArrayList();
        ResultSet rs=null;
        try
        {
            con= Conection.ligarBD();
            pstm= con.prepareStatement(buscaPor_nome);
            pstm.setString(1, nome);
            rs= pstm.executeQuery();
            while(rs.next())
            {
                Contacto c= new Contacto();
                c.setPk_contacto(rs.getInt("pk_contacto"));
                c.setNome_contacto(rs.getString("nome_contacto"));
                c.setTipo_sexo(rs.getString("desc_sexo"));
                c.setTelefone(rs.getString("telefone"));
                c.setNomeProvincia(rs.getString("desc_provincia"));
                lista.add(c);
            }
            
        }
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null,"Erro ao listar os contactos" + sqle.getMessage());
        }
        finally
        {
            Conection.closeConection(con, pstm, rs);
        }
        return lista;
    }
    
    public List<Contacto> buscaTelefone(String nome)
    {
        List<Contacto> lista= new ArrayList();
        ResultSet rs=null;
        try
        {
            con= Conection.ligarBD();
            pstm= con.prepareStatement(buscaPor_telefone);
            pstm.setString(1, nome);
            rs= pstm.executeQuery();
            while(rs.next())
            {
                Contacto c= new Contacto();
                c.setPk_contacto(rs.getInt("pk_contacto"));
                c.setNome_contacto(rs.getString("nome_contacto"));
                c.setTipo_sexo(rs.getString("desc_sexo"));
                c.setTelefone(rs.getString("telefone"));
                c.setNomeProvincia(rs.getString("desc_provincia"));
                lista.add(c);
            }
            
        }
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null,"Erro ao listar os contactos" + sqle.getMessage());
        }
        finally
        {
            Conection.closeConection(con, pstm, rs);
        }
        return lista;
    }
    
    public List<Contacto> buscaID(int id)
    {
        List<Contacto> lista= new ArrayList();
        ResultSet rs=null;
        try
        {
            con= Conection.ligarBD();
            pstm= con.prepareStatement(buscaPor_id);
            pstm.setInt(1, id);
            rs= pstm.executeQuery();
            while(rs.next())
            {
                Contacto c= new Contacto();
                c.setPk_contacto(rs.getInt("pk_contacto"));
                c.setNome_contacto(rs.getString("nome_contacto"));
                c.setTipo_sexo(rs.getString("desc_sexo"));
                c.setTelefone(rs.getString("telefone"));
                c.setNomeProvincia(rs.getString("desc_provincia"));
                lista.add(c);
            }
            
        }
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null,"Erro ao listar os contactos" + sqle.getMessage());
        }
        finally
        {
            Conection.closeConection(con, pstm, rs);
        }
        return lista;
    }
    
    public List<Contacto> buscaSexo(String nome)
    {
        List<Contacto> lista= new ArrayList();
        ResultSet rs=null;
        try
        {
            con= Conection.ligarBD();
            pstm= con.prepareStatement(buscaPor_sexo);
            pstm.setString(1, nome);
            rs= pstm.executeQuery();
            while(rs.next())
            {
                Contacto c= new Contacto();
                c.setPk_contacto(rs.getInt("pk_contacto"));
                c.setNome_contacto(rs.getString("nome_contacto"));
                c.setTipo_sexo(rs.getString("desc_sexo"));
                c.setTelefone(rs.getString("telefone"));
                c.setNomeProvincia(rs.getString("desc_provincia"));
                lista.add(c);
            }
        }
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null,"Erro ao listar os contactos" + sqle.getMessage());
        }
        finally
        {
            Conection.closeConection(con, pstm, rs);
        }
        return lista;
    }
    
    public List<Contacto> buscaProvincia(String nome)
    {
        List<Contacto> lista= new ArrayList();
        ResultSet rs=null;
        try
        {
            con= Conection.ligarBD();
            pstm= con.prepareStatement(buscaPor_provincia);
            pstm.setString(1, nome);
            rs= pstm.executeQuery();
            while(rs.next())
            {
                Contacto c= new Contacto();
                c.setPk_contacto(rs.getInt("pk_contacto"));
                c.setNome_contacto(rs.getString("nome_contacto"));
                c.setTipo_sexo(rs.getString("desc_sexo"));
                c.setTelefone(rs.getString("telefone"));
                c.setNomeProvincia(rs.getString("desc_provincia"));
                lista.add(c);
            }
        }
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null,"Erro ao listar os contactos" + sqle.getMessage());
        }
        finally
        {
            Conection.closeConection(con, pstm, rs);
        }
        return lista;
    }
    
    
}
