/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import View.ViewLogin;
import View.ViewRegister;
import Model.CekLogin;
import javax.swing.*;
import View.Transaction.ViewTransaction;
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
        
        login.getLoginButton().addActionListener(e -> {
//            String username = login.getInputUsername();
//            String password = login.getPassword();
            String username = login.getInputUsername().getText();
            String password = new String(login.getInputPassword().getPassword());

            boolean valid = cek.login(username, password);

            if (valid) {
                int userId = cek.getLoginId();
                JOptionPane.showMessageDialog(login, "Login berhasil!");
                new ViewTransaction(userId).setVisible(true);
                login.dispose();

                // lanjut ke menu utama, misalnya
            } else {
                JOptionPane.showMessageDialog(login, "Login gagal. Cek username/password.");
            }
        });
        
        login.getRegistButton().addActionListener(e -> {
            goToRegist();
        });
    }
    private void goToRegist() {
        login.dispose();
        ViewRegister registerView = new ViewRegister();
        new RegisterController(registerView);
        registerView.setVisible(true);
    }
}
