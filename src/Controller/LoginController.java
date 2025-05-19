/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import View.ViewLogin;
import Model.CekLogin;
/**
 *
 * @author HP
 */
public class LoginController {
    private ViewLogin login;
    private CekLogin cek;
    
    public LoginController(ViewLogin login, CekLogin cek) {
        this.login = login;
        this.cek = cek;
    }
    
    public void handleLogin(){
        String username = login.inputUsername.getText();
        char[] passwordChars = login.passwordField.getPassword();
        String password = new String(passwordChars);
    }
}
