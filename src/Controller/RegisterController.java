/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author HP
 */

import Model.CekRegister;
import View.ViewRegister;
import View.ViewLogin;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterController {
    private ViewRegister registerView;
    private CekRegister cekRegister;
    
     public RegisterController(ViewRegister registerView) {
        this.registerView = registerView;
        this.cekRegister = new CekRegister();
        
        registerView.getRegistButton().addActionListener(e -> {
//            JTextField username = registerView.getInputUsername();
//            JPasswordField password = registerView.getInputPassword();
//            

            String username = registerView.getInputUsername().getText();
            String password = new String(registerView.getInputPassword().getPassword());

            // Proses registrasi
            if (cekRegister.processRegistration(username, password)) {
                // Jika berhasil, pindah ke halaman login
                goToLogin();
            }
        });
        
        registerView.getLoginButton().addActionListener(e -> {
            goToLogin();
        });
    }
    
    private void goToLogin() {
        registerView.dispose();
        ViewLogin viewLogin = new ViewLogin();
        new LoginController(viewLogin);
        viewLogin.setVisible(true);
    }
}
