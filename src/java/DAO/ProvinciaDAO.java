/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.Conection;
import Modelo.Provincia;
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
public class ProvinciaDAO {
    private final String lista_provincia=" select *from provincia";
    PreparedStatement pstm;
    Connection con;
    
    public List<Provincia> listaProvncia()
    {
        List<Provincia> lista= new ArrayList();
        ResultSet rs=null;
        try
        {
            con=Conection.ligarBD();
            pstm=con.prepareStatement(lista_provincia);
            rs= pstm.executeQuery();
            while(rs.next())
            {
                Provincia p= new Provincia();
                p.setPk_provincia(rs.getInt("pk_provincia"));
                p.setNomeProvincia(rs.getString("descricao"));
                lista.add(p);
            }
        }
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null,"Erro ao listar as provincias" +sqle);
        }
        finally
        {
            Conection.closeConection(con, pstm, rs);
        }
        return lista;
    }
}
