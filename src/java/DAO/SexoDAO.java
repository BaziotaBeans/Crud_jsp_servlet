/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Conexao.Conection;
import Modelo.Sexo;
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
public class SexoDAO {
    private final String lista_sexo="select *from sexo";
    Connection con;
    PreparedStatement pstm;
    
    public List <Sexo> listaDeSexo()
    {
        List<Sexo> lista= new ArrayList();
        ResultSet rs=null;
        try
        {
            con=Conection.ligarBD();
            pstm=con.prepareStatement(lista_sexo);
            rs= pstm.executeQuery();
            while(rs.next())
            {
                Sexo s= new Sexo();
                s.setPk_sexo(rs.getInt("pk_sexo"));
                s.setTipo_sexo(rs.getString("descricao"));
                lista.add(s);
            }
        }
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null,"Erro ao listar" +sqle.getMessage());
        }
        finally
        {
            Conection.closeConection(con, pstm, rs);
        }
        return lista;
    }
}
