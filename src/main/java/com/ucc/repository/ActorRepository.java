package com.ucc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ucc.connection.DatabaseConnection;
import com.ucc.model.Actor;

public class ActorRepository implements IRepository {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstanceConnection();
    }

    @Override
    public List<Actor> findAll() throws SQLException {
        List<Actor> actors = new ArrayList<>();
        String sql = "SELECT actor_id, first_name, last_name FROM sakila.actor";

        try (Connection myConn = getConnection();
             Statement myStat = myConn.createStatement();
             ResultSet myRes = myStat.executeQuery(sql)) {

            while (myRes.next()) {
                Actor newActor = new Actor();
                newActor.setActor_id(myRes.getInt("actor_id"));
                newActor.setFirst_name(myRes.getString("first_name"));
                newActor.setLast_name(myRes.getString("last_name"));
                actors.add(newActor);
            }
        }
        return actors;
    }

    @Override
    public Actor save(Actor actor) throws SQLException {
        String sql = "INSERT INTO sakila.actor(first_name, last_name) VALUES (?, ?)";
        try (Connection myConn = getConnection();
             PreparedStatement myPrepare = myConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            myPrepare.setString(1, actor.getFirst_name());
            myPrepare.setString(2, actor.getLast_name());

            int filasAfectadas = myPrepare.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet keys = myPrepare.getGeneratedKeys()) {
                    if (keys.next()) {
                        actor.setActor_id(keys.getInt(1)); // tu POJO usa int
                    }
                }
            }
        }
        return actor;
    }
}
