/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clientesdesktop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Julian Arevalo
 */
public class OpUsersModel extends Conexion {
    
    public boolean Consultar(UserModel user) throws ClassNotFoundException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "SELECT t1.*, concat(t2.nombres,' ',t2.apellidos) as nombre, t3.cargo  FROM usuario t1 \n" +
        "inner join persona t2 on t1.id_persona=t2.id_persona\n" +
        "inner join tipo_usuario t3 on t1.id_tipo=t3.id_tipo\n" +
        "  WHERE usuario=? and pass=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsuario());
            ps.setString(2, user.getPass());
            rs = ps.executeQuery();
            
            if(rs.next()){
                user.setId_persona(rs.getInt("id_usuario"));
                user.setUsuario(rs.getString("usuario"));
                user.setPass(rs.getString("pass"));
                user.setId_persona(rs.getInt("id_persona"));
                user.setId_tipo(rs.getInt("id_tipo"));
                user.setNombre(rs.getString("nombre"));
                user.setRol(rs.getString("cargo"));
              
                return true;
            }
            
            return false;
            
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            } 
        }     
    } 

}
