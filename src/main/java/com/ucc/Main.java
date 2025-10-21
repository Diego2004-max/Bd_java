package com.ucc;

import java.sql.Connection;
import java.sql.SQLException;

import com.ucc.connection.DatabaseConnection;
import com.ucc.model.Actor;
import com.ucc.repository.ActorRepository;
import com.ucc.repository.IRepository;

public class Main {
    public static void main(String[] args) throws SQLException {
       
        try(Connection myConn = DatabaseConnection.getInstanceConnection()){
            Actor actor = new Actor();
            actor.setActor_id(9999);
            actor.setFirst_name("PepitoCode2");
            actor.setLast_name("pepitoCode2");
            
            IRepository actorRepository = new ActorRepository();
            actorRepository.save(actor);

            actorRepository.findAll().forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Conexion Fail");
        } 
    }
}   