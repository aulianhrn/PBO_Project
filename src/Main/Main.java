/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.sql.Connection;
import View.ViewLogin;
import Controller.LoginController;
import Model.CekLogin;
/**
 *
 * @author HP
 */
public class Main {
    public static void main(String[] args) {
        ViewLogin view = new ViewLogin();
        CekLogin model = new CekLogin(); 
        new LoginController(view);
        
        view.setVisible(true);
    }
}
