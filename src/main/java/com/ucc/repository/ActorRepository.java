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

public class ActorRepository implements IRepository<Actor> {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstanceConnection();
    }

    @Override
    public List<Actor> findAll() throws SQLException {
        List<Actor> actors = new ArrayList<>();
        String sql = "SELECT * FROM sakila.actor";
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Actor actor = new Actor();
                actor.setActor_id(rs.getInt("actor_id"));
                actor.setFirst_name(rs.getString("first_name"));
                actor.setLast_name(rs.getString("last_name"));
                actors.add(actor);
            }
        }
        return actors;
    }

    @Override
    public Actor save(Actor actor) throws SQLException {
        String sql = "INSERT INTO sakila.actor(actor_id, first_name, last_name) VALUES (?,?,?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, actor.getActor_id());
            ps.setString(2, actor.getFirst_name());
            ps.setString(3, actor.getLast_name());
            ps.executeUpdate();
        }
        return actor;
    }

    @Override
    public Actor update(Actor actor) throws SQLException {
        String sql = "UPDATE sakila.actor SET first_name = ?, last_name = ? WHERE actor_id = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, actor.getFirst_name());
            ps.setString(2, actor.getLast_name());
            ps.setInt(3, actor.getActor_id());
            ps.executeUpdate();
        }
        return actor;
    }

    @Override
    public void delete(int actor_id) throws SQLException {
        String sql = "DELETE FROM sakila.actor WHERE actor_id = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, actor_id);
            ps.executeUpdate();
        }
    }
}
