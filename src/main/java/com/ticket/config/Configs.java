/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ticket.config;

/**
 *
 * @author admin
 */
public class Configs {
    private static final String user="root"; 
    private static final String password ="";
    private static final String dbName ="ticket";

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }

    public static String getDbName() {
        return dbName;
    }
    
}
