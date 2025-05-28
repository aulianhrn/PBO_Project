/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import View.ViewLogin;
import Model.CekLogin;
import javax.swing.*;
/**
 *
 * @author HP
 */
public class LoginController {
    private ViewLogin login;
    private CekLogin cek;
    
    public LoginController(ViewLogin login){
        this.login = login;
        this.cek = new CekLogin();
        
        login.loginButton.addActionListener(e -> {
            String username = login.getUsername();
            String password = login.getPassword();

            boolean valid = cek.login(username, password);

            if (valid) {
                JOptionPane.showMessageDialog(login, "Login berhasil!");
                // lanjut ke menu utama, misalnya
            } else {
                JOptionPane.showMessageDialog(login, "Login gagal. Cek username/password.");
            }
        });
    }
}
