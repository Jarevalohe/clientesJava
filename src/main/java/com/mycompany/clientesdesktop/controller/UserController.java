/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clientesdesktop.controller;

import com.mycompany.clientesdesktop.model.ClientModel;
import com.mycompany.clientesdesktop.model.OpClientModel;
import com.mycompany.clientesdesktop.model.OpUsersModel;
import com.mycompany.clientesdesktop.model.UserModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.ClientView;
import view.LoginView;

/**
 *
 * @author Julian Arevalo
 */
public class UserController implements ActionListener {

    private UserModel user;
    private OpUsersModel operationusermodel;
    private LoginView loginview;

    public UserController(UserModel user, OpUsersModel operationusermodel, LoginView loginview) {
        this.user = user;
        this.operationusermodel = operationusermodel;
        this.loginview = loginview;
        this.loginview.btnIngresar.addActionListener(this);
    }

    public void Init() {
        loginview.setTitle("Ingreso al sistema");
        loginview.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginview.btnIngresar) {
            user.setUsuario(loginview.txtUsuario.getText());
            user.setPass(loginview.txtContrasena.getText());
            try {
                if(loginview.txtUsuario.getText().length()==0 && loginview.txtContrasena.getText().length()==0){
                    JOptionPane.showMessageDialog(null, "Debe ingresar usuario y contraseña asignado");
                    return;
                }
                else if(loginview.txtUsuario.getText().length()==0){
                    JOptionPane.showMessageDialog(null, "Debe ingresar usuario");
                    return;
                }
                else if(loginview.txtContrasena.getText().length()==0){
                    JOptionPane.showMessageDialog(null, "Debe ingresar contraseña");
                    return;
                }
                if (operationusermodel.Consultar(user)) {
                    JOptionPane.showMessageDialog(null, "Acceso correcto");
                    loginview.setVisible(false);
                    OpClientModel opclientmodel = new OpClientModel();
                    ClientView clientview = new ClientView();
                    ClientModel client = new ClientModel();
                    ClientController ctrlclient = new ClientController(client,user,opclientmodel,clientview);
                    ctrlclient.Init();
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciales no registradas en el sistema, comuníquese con el administrador");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
