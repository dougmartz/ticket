/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ticket.models;

import com.ticket.controllers.LoginController;
import com.ticket.views.Login;
import com.ticket.views.Menu;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 *
 * @author admin
 */
public class LoginModel {
    private UserDAO userDAO;
    private ArrayList<UserVO> users;
    private Login login;
    private LoginController controller;
    private String message;
    private boolean showMessage;
    public LoginModel(Login login) {
        this.login = login;
        userDAO = new UserDAO();
        controller = new LoginController(this);
        users = userDAO.read();
    }

    public LoginController getController() {
        return controller;
    }
    
    public void logIn(){
        String username = login.getUsername(),
               password = login.getPassword();
        try {
            if (username.isEmpty()) {
            // if the user's JTextfield its empty.
                message = "Please insert a Username.";
                showMessage = true;
            }
            else if (password.isEmpty()) {
            //if the JPasswordField its empty.
                message = "Please insert the password.";
                showMessage = true;
            }
            else {
                for (UserVO user : users) {
                    if (user.getUsername().equals(username) && 
                        user.getPassword().equals(password)) {
                        new Menu();
                        login.dispose();
                        showMessage = false;
                    }
                    else {
                        message = "Wrong User or Password";
                        showMessage = true;
                    }
                }
            }
            if (showMessage) {
            JOptionPane.showMessageDialog(null, message, 
                    "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("Error in Login: " +e);
        }
        
    }
    
}
