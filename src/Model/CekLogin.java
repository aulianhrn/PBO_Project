/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.*;

/**
 *
 * @author HP
 */
public class CekLogin {
    Connection conn; //ambil connector
    int loginId; //nyimpen id yang berhasil login
    
    public CekLogin() {
        conn = Connector.connect();
    }
    
    public boolean login(String username, String password) {
        boolean valid = false;        

        try {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

//            boolean result = rs.next(); // true jika data ditemukan
                        
            if (rs.next()) {
                loginId = rs.getInt("id"); // sesuaikan nama kolomnya di DB
                rs.close();
                statement.close();
                return true;
            }            
            
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Login gagal: " + e.getMessage());
        }

        return false;
    }
    
    public int getLoginId() {
        return loginId;
    }
}
