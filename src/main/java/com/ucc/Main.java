package com.ucc;

import java.sql.Connection;
import java.sql.SQLException;

import com.ucc.connection.DatabaseConnection;
import com.ucc.model.Actor;
import com.ucc.repository.ActorRepository;
import com.ucc.repository.IRepository;

public class Main {
    public static void main(String[] args) throws SQLException {
       
        try (Connection myConn = DatabaseConnection.getInstanceConnection()) {

            Actor actor = new Actor();
            actor.setActor_id(9999);
            actor.setFirst_name("PepitoCode2");
            actor.setLast_name("pepitoCode2");
            
            IRepository<Actor> actorRepository = new ActorRepository();

            // 🔹 Evita el error por clave duplicada
            actorRepository.delete(9999);

            // Insertar actor
            actorRepository.save(actor);
            System.out.println("Se insertó el actor correctamente");

            // Actualizar actor
            actor.setFirst_name("PepitoActualizado");
            actor.setLast_name("CodeActualizado");
            actorRepository.update(actor);
            System.out.println("Se actualizó el actor correctamente");

            // Eliminar actor
            actorRepository.delete(9999);
            System.out.println("Se eliminó el actor correctamente");

            // Listar actores
            actorRepository.findAll().forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } 
    }
}
