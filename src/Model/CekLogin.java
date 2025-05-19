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
    
    private static String username;
    private static String password;
    
    
    public CekLogin() {
        conn = Connector.connect();
    }
    
    public boolean login(String username, String password) {
        boolean valid = false;

        try {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, username);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                valid = true;
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println("Login gagal: " + e.getMessage());
        }

        return valid;
    }
}
