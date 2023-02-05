/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clientesdesktop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Julian Arevalo
 */
public class OpClientModel extends Conexion {

    public ArrayList<ClientModel> Consultar(UserModel user) throws ClassNotFoundException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "";
        switch (user.getId_tipo()) {
            case 1:
                sql = "SELECT *, case when tienda=1 then 'Tienda 1' else 'Tienda 2' end AS tiendanombre FROM persona WHERE colaborador=false";
                break;
            case 2:
                sql = "SELECT *, case when tienda=1 then 'Tienda 1' else 'Tienda 2' end AS tiendanombre FROM persona WHERE colaborador=false and tienda=1";
                break;
            case 3:
                sql = "SELECT *, case when tienda=1 then 'Tienda 1' else 'Tienda 2' end AS tiendanombre FROM persona WHERE colaborador=false and tienda=2";
                break;
            default:
                throw new AssertionError();
        }
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList<ClientModel> clients = new ArrayList<ClientModel>();
            while (rs.next()) {
                ClientModel client = new ClientModel();
                client.setId_persona(rs.getInt("id_persona"));
                client.setNombres(rs.getString("nombres"));
                client.setApellidos(rs.getString("apellidos"));
                client.setTelefono(rs.getString("telefono"));
                client.setCorreo(rs.getString("correo"));
                client.setColaborador(rs.getBoolean("colaborador"));
                client.setTienda(rs.getInt("tienda"));
                client.setNombreTienda(rs.getString("tiendanombre"));
                clients.add(client);
            }
            return clients;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

}
