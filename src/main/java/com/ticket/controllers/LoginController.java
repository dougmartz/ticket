/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ticket.controllers;

import com.ticket.models.LoginModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author admin
 */
public class LoginController implements ActionListener{
    LoginModel model;
    public LoginController(LoginModel model) {
        this.model = model;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Log in")) {
            model.logIn();
            System.out.println("Login");
        }
    }
    
}
