/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ticket.controllers;

import com.ticket.models.MenuModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author admin
 */
public class MenuController implements ActionListener{
    private MenuModel menuModel;

    public MenuController(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Save")) {
            menuModel.save();
            System.out.println("Save button pressed");
        }
        if (e.getActionCommand().equals("Delete")) {
            menuModel.delete();
            System.out.println("Delete button pressed");
        }
        if (e.getActionCommand().equals("Edit")) {
            menuModel.edit();
            System.out.println("Edit button pressed");
        }
        if (e.getActionCommand().equals("Add")) {
            menuModel.add();
            System.out.println("Add button pressed");
        }
    }
    
}
