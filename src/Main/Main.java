/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.sql.Connection;
import View.ViewLogin;
import Controller.LoginController;
import Controller.RegisterController;
import Model.CekLogin;
import Model.CekRegister;
import View.ViewRegister;
/**
 *
 * @author HP
 */
public class Main {
    public static void main(String[] args) {
        ViewLogin view = new ViewLogin();
        new LoginController(view);
        view.setVisible(true);
//           ViewRegister viewRegister = new ViewRegister();
//           RegisterController controller = new RegisterController(viewRegister);
//        viewRegister.setVisible(true);
    }
}
