/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model;

/**
 *
 * @author Lenovo
 */
public interface InterfaceUserDAO {
    public boolean isUsernameExists(String username);
    
    public boolean registerUser(String username, String password);
    
    public int validateUser(String username, String password);
}
