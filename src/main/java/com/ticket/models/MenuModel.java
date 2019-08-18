/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ticket.models;

import com.ticket.controllers.MenuController;
import com.ticket.views.Menu;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Douglas
 */
public class MenuModel {
    private Menu menu;
    private MenuController menuController;
    private TicketDAO ticketDAO;
    private ArrayList<TicketVO> tickets;
    private boolean showMessage = true;
    private String message;
    
    public MenuModel(Menu menu) {
        this.menu = menu;
        menuController = new MenuController(this);
        ticketDAO = new TicketDAO();
        tickets = ticketDAO.read(); // Add the db values into the aarylist.
        this.addRows();
    }

    public MenuController getMenuController() {
        return menuController;
    }
    
    public void save(){
        try {
            int rowSlec = menu.getSlecRow();// Get value of selected row.
            if (rowSlec >= 0) { // If is selected continue.
                    TicketVO ticket = new TicketVO(); // Initialize the new ticket.
                    this.setValues(ticket, rowSlec); // Set the new values of the tickets;
                    boolean saved = ticketDAO.create(ticket);
                    if (saved){
                        // If saved reload the JTable.
                        System.out.println("Saved in server");
                        message = "Saved in server";
                        tickets.clear();
                        tickets = ticketDAO.read();
                        menu.cleanTable();
                        this.addRows();
                    }
                    else {
                        System.out.println("Something wrongs");
                        message = "Something wrongs";
                    }
            }
            else {
                message = "Please select one row";
            }
            if (showMessage) {
            JOptionPane.showMessageDialog(null, message, 
                    "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception exc) {
            System.err.println("Error cant save: " +exc);
        }
    }
    
    public void delete(){
        try {
            int rowSlec = menu.getSlecRow();// Get value of selected row.
            if (rowSlec >= 0) { // If is selected continue.
                // Parsing the id from the selected row.
                Long id = Long.parseLong(menu.getModel().getValueAt(rowSlec, 0).toString());
                boolean deleted = ticketDAO.delete(id);
                if (deleted) {
                    //If was deleted we clear the list and we updated the JTable.
                    System.out.println("Deleted succes");
                    message = "Deleted succes";
                    tickets.clear();
                    tickets = ticketDAO.read();
                    menu.cleanTable();
                    this.addRows();
                }
                else {
                    System.out.println("Something wrongs");
                    message = "Something wrongs";
                }
            }
            else {
                message = "Please select one row";
            }
            if (showMessage) {
            JOptionPane.showMessageDialog(null, message, 
                    "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception exc) {
            System.err.println("Error cant updated: " +exc);
        }
    }
    
    public void edit(){
        try {
            int rowSlec = menu.getSlecRow(); // Get value of selected row.
            if (rowSlec >= 0) { // If is selected continue.
                for (TicketVO ticket : tickets) { // We go through the array to check the tickets list.
                    String id = menu.getModel().getValueAt(rowSlec, 0).toString();
                    // If the id is equal to the selected id row value we update.
                    if (ticket.getId().toString().equals(id)){ 
                        this.setValues(ticket, rowSlec);
                        boolean edited = ticketDAO.update(ticket);
                        if (edited) {
                            message = "Updated succes";// Succesful
                            System.out.println("Updated succes");
                        }
                        else {
                            message = "Something wrongs";
                            System.out.println("Something wrongs");
                        }
                    }
                }
            }
            else {
                 message = "Please select one row";
            }
            if (showMessage) {
            JOptionPane.showMessageDialog(null, message, 
                    "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception exc) {
            System.err.println("Error cant updated: " +exc);
        }
    }
    
    public void add(){
        menu.addRow(new Object[0]);
    }

    private void addRows(){
        String[] newRow = new String [10]; // Create a new array to host the data;
        for (int i = 0; i < tickets.size(); i++) {
            //Getting the values from the list
            String.valueOf(tickets.get(i).getId());
            newRow[1] = tickets.get(i).getType();
            newRow[2] = tickets.get(i).getName();
            newRow[3] = tickets.get(i).getVenue();
            newRow[4] = tickets.get(i).getDate().toString();
            newRow[5] = tickets.get(i).getPrice().toString();
            newRow[6] = String.valueOf(tickets.get(i).getNumber());
            newRow[7] = tickets.get(i).getRemarks();
            newRow[8] = tickets.get(i).getCustomer();
            newRow[9] = tickets.get(i).getPhone();
            menu.addRow(newRow); // Sending to the JTable.
        }
    }
    
    //Getting the values from JTable.
    private void setValues(TicketVO ticket, int rowSlec){
        Date simpleDate = null;
        ticket.setType(menu.getModel().getValueAt(rowSlec, 1).toString());
        ticket.setName(menu.getModel().getValueAt(rowSlec, 2).toString());
        ticket.setVenue(menu.getModel().getValueAt(rowSlec, 3).toString());
        String date = menu.getModel().getValueAt(rowSlec, 4).toString();
        try {
            simpleDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            ticket.setDate(simpleDate);
        } catch (ParseException ex) {/*...*/}
        ticket.setPrice(Double.parseDouble(menu.getModel().getValueAt(rowSlec, 5).toString()));
        ticket.setNumber(Integer.valueOf(menu.getModel().getValueAt(rowSlec, 6).toString()));
        ticket.setRemarks(menu.getModel().getValueAt(rowSlec, 7).toString());
        ticket.setCustomer(menu.getModel().getValueAt(rowSlec, 8).toString());
        ticket.setPhone(menu.getModel().getValueAt(rowSlec, 9).toString());
    }
}
