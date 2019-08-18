/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ticket.models;

import com.ticket.database.Connect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class TicketDAO {
    Connection cn;
    Connect connect;
    ResultSet rs;
    Statement st;
    PreparedStatement pst;
    final static String INSERT="INSERT INTO tickets "
                            + "(type, name, venue, date, "
                            + "price, number, remarks, customer, phone) "
                            + "VALUES(?,?,?,?,?,?,?,?,?)";
    final static String UPDATE="UPDATE tickets "
                            + "SET type = ?, name= ?, venue=?, date=?, "
                            + "price=?, number=?, remarks=?, "
                            + "customer=?, phone=?"
                            + "WHERE id = ?";
    final static String DELETE="DELETE FROM tickets WHERE id = ?";
    final static String GETALL="SELECT id, type, name, venue, "
                            + "date, price, number, remarks, "
                            + "customer, phone "
                            + "FROM tickets";
    public TicketDAO() {
        cn = null;
        rs = null;
        pst = null;
        connect = new Connect();
    }
    
    public boolean create(TicketVO ticket){
        try {
            cn = connect.dataSource.getConnection();
            //Auto commit turn off
            cn.setAutoCommit(false);
            
            pst = cn.prepareStatement(INSERT);
            pst.setString(1, ticket.getType());
            pst.setString(2, ticket.getName());
            pst.setString(3, ticket.getVenue());
            pst.setDate(4, new Date(ticket.getDate().getTime()));
            pst.setDouble(5, ticket.getPrice());
            pst.setInt(6, ticket.getNumber());
            pst.setString(7, ticket.getRemarks());
            pst.setString(8, ticket.getCustomer());
            pst.setString(9, ticket.getPhone());
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
    
    public boolean update(TicketVO ticket){
        try {
            cn = connect.dataSource.getConnection();
            cn.setAutoCommit(false);
            pst = cn.prepareStatement(UPDATE);
            pst.setString(1, ticket.getType());
            pst.setString(2, ticket.getName());
            pst.setString(3, ticket.getVenue());
            pst.setDate(4, new Date(ticket.getDate().getTime()));
            pst.setDouble(5, ticket.getPrice());
            pst.setInt(6, ticket.getNumber());
            pst.setString(7, ticket.getRemarks());
            pst.setString(8, ticket.getCustomer());
            pst.setString(9, ticket.getPhone());
            pst.setLong(10, ticket.getId());
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
        Date date;
        try {
            cn = connect.dataSource.getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(GETALL);
            while (rs.next()) {
                TicketVO ticket = new TicketVO();
                ticket.setId(rs.getLong("id"));
                ticket.setType(rs.getString("type"));
                ticket.setName(rs.getString("name"));
                ticket.setVenue(rs.getString("venue"));
                date = rs.getDate("date");
                ticket.setDate(date);
                ticket.setPrice(rs.getDouble("price"));
                ticket.setNumber(rs.getInt("number"));
                ticket.setRemarks(rs.getString("remarks"));
                ticket.setCustomer(rs.getString("customer"));
                ticket.setPhone(rs.getString("phone"));
                
                list.add(ticket);
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
