/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class CekLogin {
    private UserDAO userDao;
    private int loginId;
    
    public CekLogin() {
        this.userDao = new UserDAO();
    }
    
    public boolean login(String username, String password) {
        this.loginId = userDao.validateUser(username, password);
        return this.loginId != -1;
    }
    
    public int getLoginId() {
        return this.loginId;
    }
}
