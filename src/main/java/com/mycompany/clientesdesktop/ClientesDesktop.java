/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.clientesdesktop;

import com.mycompany.clientesdesktop.controller.UserController;
import com.mycompany.clientesdesktop.model.OpUsersModel;
import com.mycompany.clientesdesktop.model.UserModel;
import view.LoginView;

/**
 *
 * @author Julian Arevalo
 */
public class ClientesDesktop {

    public static void main(String[] args) {
                UserModel user = new UserModel();
        OpUsersModel opusersmodel = new OpUsersModel();
        LoginView loginview = new LoginView();
        
        UserController ctrl = new UserController(user, opusersmodel, loginview);
        ctrl.Init();
        loginview.setVisible(true);
    }
}
