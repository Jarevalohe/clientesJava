/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clientesdesktop.controller;

import com.mycompany.clientesdesktop.ClientesDesktop;
import com.mycompany.clientesdesktop.model.ClientModel;
import com.mycompany.clientesdesktop.model.OpClientModel;
import com.mycompany.clientesdesktop.model.UserModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import view.ClientView;

/**
 *
 * @author Julian Arevalo
 */
public class ClientController implements ActionListener {

    private ClientModel client;
    private UserModel user;
    private OpClientModel operationclientmodel;
    private ClientView clientview;

    public ClientController(ClientModel client, UserModel user, OpClientModel operationclientmodel, ClientView clientview) {
        this.client = client;
        this.user = user;
        this.operationclientmodel = operationclientmodel;
        this.clientview = clientview;
        this.clientview.btnCerrarSesion.addActionListener(this);
    }

    public void Init() {
        //loginview.setTitle("Ingreso al sistema");
        //loginview.setLocationRelativeTo(null);
        clientview.setTitle("Clientes");
        clientview.setVisible(true);
        clientview.setLocationRelativeTo(null);
        clientview.lblNombre.setText(user.getNombre());
        clientview.lblRol.setText(user.getRol());
        clientview.lblUsuario.setText(user.getUsuario());
        CargarDatos();
    }

    public void CargarDatos() {
        try {
            ArrayList<ClientModel> l = new ArrayList();
            l = operationclientmodel.Consultar(user);
            
            DefaultTableModel d = new DefaultTableModel(new String[]{"nombres","apellidos","telefonos","correo","tiendanombre"},l.size());
            clientview.tableCliente.setModel(d);
            TableModel modeloDatos = clientview.tableCliente.getModel();
            for (int i = 0; i <= l.size()-1; i++) {
                client = l.get(i);
                modeloDatos.setValueAt(client.getNombres(), i, 0);
                modeloDatos.setValueAt(client.getApellidos(), i, 1);
                modeloDatos.setValueAt(client.getTelefono(), i, 2);
                modeloDatos.setValueAt(client.getCorreo(), i,3);
                modeloDatos.setValueAt(client.getNombreTienda(), i,4);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clientview.btnCerrarSesion) {
            JOptionPane.showMessageDialog(null, "Se cerró la sesión");
            clientview.setVisible(false);
            ClientesDesktop.main(null);
        }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
