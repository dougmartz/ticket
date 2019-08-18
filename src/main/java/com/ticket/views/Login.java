/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ticket.views;

import com.ticket.models.LoginModel;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author admin
 */
public class Login extends JFrame {
    private JPanel loginPanel;
    private JButton btnLogin;
    private JLabel labelUser, labelPassword;
    private JTextField userField;
    private JPasswordField passwordField;
    private Font font;
    private LoginModel model;
    
    public Login(){
        initSettings();
        initElements();
        setupeElements();
        finalSettings();        
    }
    
    private void initSettings(){
        this.setSize(500, 350);
        this.setLocationRelativeTo(null) ;
    }
    
    private void initElements(){
        loginPanel = new JPanel();
        btnLogin =  new JButton();
        labelUser = new JLabel("Username");
        labelPassword = new JLabel("Password");
        userField = new JTextField();
        passwordField = new JPasswordField();
        font = new Font("Tahoma", 1, 14);
        model = new LoginModel(this);
    }
    
    private void setupeElements(){
        loginPanel.setBackground(new java.awt.Color(0,102,153));
        loginPanel.setLayout(null);
        
        btnLogin.setBounds(150, 219, 197, 32);
        btnLogin.setFont(font);
        btnLogin.setText("Log in");
        btnLogin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        
        labelUser.setBounds(150, 47, 197, 30);
        labelUser.setFont(font);
        
        labelPassword.setBounds(150, 119, 197, 30);
        labelPassword.setFont(font);
        
        userField.setBounds(150, 83, 197, 30);
        userField.setPreferredSize(new Dimension(150, 30));
        userField.setFont(font);
        userField.setForeground(new java.awt.Color(0, 153, 255));
        
        passwordField.setBounds(150, 155, 197, 30);
        passwordField.setPreferredSize(new Dimension(150, 30));
        passwordField.setFont(font);
        passwordField.setForeground(new java.awt.Color(0, 153, 255));
        //this.showCharacter(true);
        
        btnLogin.addActionListener(model.getController());
        loginPanel.add(labelUser);
        loginPanel.add(userField);
        loginPanel.add(labelPassword);
        loginPanel.add(passwordField);
        loginPanel.add(btnLogin);
    	this.getContentPane().add(loginPanel);
    }
    
    private void finalSettings(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    /*
    private void showCharacter(boolean show){
        if (show) passwordField.setEchoChar((char)0);
        else passwordField.setEchoChar('*');
    }
    */
    public String getUsername(){
        return userField.getText();
    }
    
    public String getPassword(){
        return passwordField.getText().toString();
    }
}
