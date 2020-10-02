/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author LENOVO
 */
public class Conection {
    private static final String DRIVER="org.postgresql.Driver";
    private static final String URL="jdbc:postgresql://localhost:5432/MyAgenda";
    private static final String USER="postgres";
    private static final String PASSWORD="postgres";

    public static Connection ligarBD()
    {
        try
        {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch(SQLException | ClassNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null,"Erro ao conectar ou class nao encontrada" +ex);
        }

        return null;
    }
    public static void closeConection(Connection con)
    {
        try
        {
            if(con!=null)
                con.close();
        }
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null,"Erro ao fechar a conexao"+ sqle);
        }
    }
    public static void closeConection(Connection con, PreparedStatement pstm)
    {
        closeConection(con);
        try
        {
            if(pstm!=null)
                pstm.close();
        }
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null,"Erro na conexao"+ sqle);
        }

    }
    public static void closeConection(Connection con, PreparedStatement pstm,ResultSet rs)
    {
        closeConection( con,  pstm);
        try
        {
            if(rs!=null)
                rs.close();
        }
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null,"Erro ao conectar" +sqle);
        }
    }
}
