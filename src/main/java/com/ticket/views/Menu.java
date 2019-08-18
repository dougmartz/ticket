/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ticket.views;

import com.ticket.models.MenuModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Douglas
 */
public class Menu extends JFrame{
    private JPanel menuPanel;
    private JTable jtable;
    private JScrollPane scrollPane;
    private JButton btnSave, 
                    btnDelete, 
                    btnEdit, 
                    btnAdd;
    private DefaultTableModel model;
    private MenuModel menuModel;
    private String [] columns;
    public Menu() {
        initSettings();
        initElements();
        setupElements();
        finalSettings();
    }
    
    private void initSettings(){
        columns = new String[] {"id","event type", "event name", "venue", 
                                "date & time", "tickets price","n° of tickets", 
                                "remarks", "costumer" , "phone" };
    }
    
    private void initElements(){
        menuPanel = new JPanel();
        jtable = new JTable();
        scrollPane = new JScrollPane(jtable);
        model = new DefaultTableModel(null, columns);
        btnSave = new JButton("Save");
        btnDelete = new JButton("Delete");
        btnEdit = new JButton("Edit");
        btnAdd = new JButton("Add");
        menuModel = new MenuModel(this);
    }
    
    private void setupElements(){
        menuPanel.setBackground(new java.awt.Color(0,102,153));
        jtable.setModel(model);
        
        btnSave.addActionListener(menuModel.getMenuController());
        btnDelete.addActionListener(menuModel.getMenuController());
        btnEdit.addActionListener(menuModel.getMenuController());
        btnAdd.addActionListener(menuModel.getMenuController());

        menuPanel.add(scrollPane);
        menuPanel.add(btnSave);
        menuPanel.add(btnDelete);
        menuPanel.add(btnEdit);
        menuPanel.add(btnAdd);
        this.getContentPane().add(menuPanel);
    }
    
    private void finalSettings(){
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null) ;
        this.setVisible(true);
    }
    
    public void addRow(Object[] row){
        model.addRow(row);
    }
    
    public int getSlecRow(){
        return jtable.getSelectedRow();
    }
    public String getTableValueAt(int rowSlec, int column){
        return model.getValueAt(rowSlec, column).toString();
    }

    public DefaultTableModel getModel() {
        return model;
    }
    
    public void cleanTable(){
        for (int i = jtable.getRowCount()-1; i >= 0; i--) {
            model.removeRow(i);
        }
    }
}
