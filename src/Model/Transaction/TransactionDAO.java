/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Transaction;

import Model.Connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 *
 * @author HP
 */
public class TransactionDAO {
    private int userId;
            
    public void insert(ModelTransaction transaction, int userId) {
        this.userId = userId;
        try{
            String query = "INSERT INTO transaction (user_id, category, amount, date, description) VALUES (?, ?, ?, ?, ?);";
            
            PreparedStatement statement;
            statement = Connector.connect().prepareStatement(query);
            statement.setInt(1, userId); 
            statement.setString(2, transaction.getCategory());
            statement.setInt(3, transaction.getAmount());
            statement.setDate(4, new java.sql.Date(transaction.getDate().getTime()));
            statement.setString(5, transaction.getDescription());

            statement.executeUpdate();         
            statement.close();
        } catch (SQLException e) {
            System.out.println("Input Failed: " + e.getLocalizedMessage());
        }
    }
    
    public void update(ModelTransaction transaction) {
        try {
            // Perintah query disimpan ke dalam variabel "query"
            String query = "UPDATE transaction SET category = ?, amount = ?, date = ?, description = ? WHERE id=?;";

            PreparedStatement statement;
            statement = Connector.connect().prepareStatement(query);
            statement.setString(1, transaction.getCategory());
            statement.setInt(2, transaction.getAmount());
            statement.setDate(3, new java.sql.Date(transaction.getDate().getTime()));
            statement.setString(4, transaction.getDescription());
            statement.setInt(5, transaction.getId());
            
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            // Menampilkan pesan error ketika gagal edit data.
            System.out.println("update Failed! (" + e.getMessage() + ")");
        }
    }
       
    public void delete (int id) {
        try {
            // Perintah query disimpan ke dalam variabel "query"
            String query = "DELETE FROM transaction WHERE id = ? ;";

            PreparedStatement statement;
            statement = Connector.connect().prepareStatement(query);
            statement.setInt(1, id);
            
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            // Menampilkan pesan error ketika gagal edit data.
            System.out.println("Delete Failed! (" + e.getMessage() + ")");
        }
    }
    
    public List<ModelTransaction> getAll(int userId) {
        this.userId = userId;
        
        List<ModelTransaction> listTransaction = null;

        try {
            listTransaction = new ArrayList<>();                        

            String query = "SELECT id, category, amount, date, description FROM transaction WHERE user_id = ?";
            PreparedStatement statement = Connector.connect().prepareStatement(query);
            statement.setInt(1, userId);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                ModelTransaction trs = new ModelTransaction();
                
                trs.setId(resultSet.getInt("id"));
                trs.setDate(resultSet.getDate("date"));
                trs.setDescription(resultSet.getString("description"));
                trs.setAmount(resultSet.getInt("amount"));
                trs.setCategory(resultSet.getString("category"));
                listTransaction.add(trs);
            }
            
            statement.close();
        } catch (SQLException e) {
            // Menampilkan pesan error ketika gagal mengambil data.
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return listTransaction;
    }
}
