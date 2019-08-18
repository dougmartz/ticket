/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ticket.database;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.swing.JOptionPane;
import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author admin
 */
public class Connect {
    private Connection connect;
    private BasicDataSource basicData;
    public DataSource dataSource;
    private String user, password, dbName, driver, timeZone, message;
    
    public Connect() {
        initSettings();
        initElements();
        setupElements();
        finalSettings();
    }
    
    private void initSettings(){
        user = "root";
        password = "";
        dbName = "ticket";
        driver = "com.mysql.jdbc.Driver";
        // Specifying the serverTimezone to making work with 5.1.33 of MySQL JDBC driver.
        timeZone= "?useUnicode=true"
                + "&useJDBCCompliantTimezoneShift=true"
                + "&useLegacyDatetimeCode=false"
                + "&serverTimezone=UTC";
    }
    
    private void initElements(){
        basicData = new BasicDataSource();
    }
    
    private void setupElements(){
        basicData.setDriverClassName(driver);
        basicData.setUsername(user);
        basicData.setPassword(password);
        basicData.setUrl("jdbc:mysql://localhost/"+dbName+timeZone);
        basicData.setMaxActive(100);
        basicData.setMinIdle(0);
        basicData.setMaxIdle(0);
        dataSource = basicData;
        try{
            connect = dataSource.getConnection();
            System.out.println("Conected to server");
        } catch(SQLException e){
            message = "Error server connection";
            JOptionPane.showMessageDialog(null, message,
                    "Warning", JOptionPane.WARNING_MESSAGE);
            System.err.println(message+": "+e);
        }
        finally{
            try {
                if (connect!= null) connect.close();
            } catch (Exception e) { System.out.println(""+e);}
        }
    }
    
    private void finalSettings(){ /*...*/ }
    
}
