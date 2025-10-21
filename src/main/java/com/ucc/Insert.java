package com.ucc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.ucc.connection.DatabaseConnection;

public class Insert {
    
    public static void main(String[] args) throws SQLException {
        
        try(Connection myConn = DatabaseConnection.getInstanceConnection();){
        
        String sql = "INSERT INTO sakila.actor(first_name,last_name) VALUES (?,?,?)";
        PreparedStatement myPrepare = myConn.prepareStatement(sql);  
        myPrepare.setString(1, "Nombre2 Aleatorio");
        myPrepare.setString(2, "Apellido2 Aleatorio");    
        myPrepare.setString(3, "Apellido3 Aleatorio");    
        
        int filasAfectadas = myPrepare.executeUpdate();

        if(filasAfectadas > 0){
            System.out.println("Se creo el actor correctamente");
        }

        } catch (Exception e) {
            System.out.println("Conexion Fail");
        } 
    }
}