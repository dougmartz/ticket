/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ticket.models;

import com.ticket.database.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class UserDAO {
    Connection cn;
    Connect connect;
    ResultSet rs;
    Statement st;
    PreparedStatement pst, pstCustomer;
    final static String INSERT="INSERT INTO users "
                            + "(username, password) "
                            + "VALUES(?,?)";
    final static String UPDATE="UPDATE users "
                            + "SET password =?"
                            + "WHERE id = ?";
    final static String DELETE="DELETE FROM users WHERE id = ?";
    final static String GETALL="SELECT id, username, password "
                            + "FROM users";
    
    public UserDAO() {
        initSettings();
        initElements();
        setupElements();
        finalSettings();
    }
    
    private void initSettings(){
        cn = null;
        rs = null;
        pst = null;
        pstCustomer = null;
    }
    
    private void initElements(){
        connect = new Connect();
    }
    
    private void setupElements(){}
    private void finalSettings(){}
    
    public boolean create(UserVO user){
        try {
            cn = connect.dataSource.getConnection();
            //Auto commit turn off
            cn.setAutoCommit(false);
            pst = cn.prepareStatement(INSERT);
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.executeUpdate();
            cn.setAutoCommit(true);
            return true;
        } catch (SQLException sqle) { // Catching any exception.
            sqle.printStackTrace();
            try {
                cn.rollback();
            } catch (SQLException sqle2) {
                System.err.println("Error at insert :" +sqle2);
            }
            return false;
        }finally {
            if (pst != null)try {pst.close();} catch (Exception e) {/*...*/}
            if (cn != null) try {cn.close();} catch (Exception e) {/*...*/}
        }
    }
    
    public boolean update(Long id, String newValue){
        try {
            cn = connect.dataSource.getConnection();
            cn.setAutoCommit(false);
            pst = cn.prepareStatement(UPDATE);
            pst.setString(1, newValue);
            pst.setLong(2, id);
            pst.executeUpdate();
            cn.commit();
            cn.setAutoCommit(true);
            return true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            try {
                cn.rollback();
            } catch (Exception sqle2) {
                System.out.println(""+sqle2);
            }
            return false;
        }
        finally{
            if (pst != null) try {pst.close();} catch (Exception e) { /*...*/ }
            if (cn != null) try {cn.close();} catch (Exception e) { /*...*/ }
        }
    }
    
    public boolean delete(Long id){
        try {
            cn = connect.dataSource.getConnection();
            pst = cn.prepareStatement(DELETE);
            pst.setLong(1, id);
            pst.execute();
            return true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            try {
                cn.rollback();
            } catch (Exception sqle2) {
                System.out.println(""+sqle2);
            }
            return false;
        }
        finally{
            if (pst != null) try {pst.close();} catch (Exception e) { /*...*/ }
            if (cn != null) try {cn.close();} catch (Exception e) { /*...*/ }
        }
    }
    
    public ArrayList read(){
        ArrayList list = new ArrayList<>();
        try {
            cn = connect.dataSource.getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(GETALL);
            while (rs.next()) {
                UserVO user = new UserVO();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                list.add(user);
            }
        } catch (Exception e) {
            System.err.println("Error getting data"
                            + "\nError: "+e);
        }
        finally {
            if (st != null) try{st.close();}catch (SQLException e){ /*...*/ }
            if (rs != null) try{rs.close();}catch (SQLException e){ /*...*/ }
            if (cn != null) try{cn.close();}catch (SQLException e){ /*...*/ }
        }
        return  list;
    }
}
