/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assistente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class Conection {
    private Connection con;
    
    Conection(){
        try {                        
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/assistente","root","");            
            //st.executeUpdate("insert into alunos values(default, '" + imprimir1 + "', '" + imprimir2 + "', '0','0')");
            JOptionPane.showMessageDialog(null, "Os dados foram inseridos com sucesso!");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "O Driver não está na biblioteca");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ns conexão com o banco de dados!");
        }
    }
    
    public int executaSQL(String sql){
        try {
            Statement stm = con.createStatement();
            int res = stm.executeUpdate(sql);
            con.close();
            return res;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "O insert falhou!");
            return 0;
        }
        
    }
    
      
}
