package com.poly;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonServiceImpl implements PersonService {
    
    @Override
    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();
        try {
            Connection conn = ConnexionDB.getConnection();
            if (conn == null) {
                System.out.println("Erreur: Connexion à la base de données échouée");
                return persons;
            }
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM persons");
            
            while (rs.next()) {
                Person p = new Person();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setAge(rs.getInt("age"));
                persons.add(p);
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erreur SQL: " + e.getMessage());
            e.printStackTrace();
        }
        return persons;
    }
    
    @Override
    public Person getPerson(int id) {
        Person person = null;
        try {
            Connection conn = ConnexionDB.getConnection();
            if (conn == null) return null;
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM persons WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setAge(rs.getInt("age"));
            }
            
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erreur SQL: " + e.getMessage());
            e.printStackTrace();
        }
        return person;
    }
    
    @Override
    public Person getPersonByName(String name) {
        Person person = null;
        try {
            Connection conn = ConnexionDB.getConnection();
            if (conn == null) return null;
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM persons WHERE name = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setAge(rs.getInt("age"));
            }
            
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erreur SQL: " + e.getMessage());
            e.printStackTrace();
        }
        return person;
    }
    
    @Override
    public void addPerson(Person person) {
        try {
            Connection conn = ConnexionDB.getConnection();
            if (conn == null) {
                System.out.println("Impossible d'ajouter: Pas de connexion");
                return;
            }
            
            PreparedStatement ps = conn.prepareStatement("INSERT INTO persons (name, age) VALUES (?, ?)");
            ps.setString(1, person.getName());
            ps.setInt(2, person.getAge());
            int result = ps.executeUpdate();
            
            System.out.println("Personne ajoutée: " + result + " ligne(s) affectée(s)");
            
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Override
    public void updatePerson(Person person) {
        try {
            Connection conn = ConnexionDB.getConnection();
            if (conn == null) {
                System.out.println("Impossible de modifier: Pas de connexion");
                return;
            }
            
            PreparedStatement ps = conn.prepareStatement("UPDATE persons SET name = ?, age = ? WHERE id = ?");
            ps.setString(1, person.getName());
            ps.setInt(2, person.getAge());
            ps.setInt(3, person.getId());
            int result = ps.executeUpdate();
            
            System.out.println("Personne modifiée: " + result + " ligne(s) affectée(s)");
            
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Override
    public void deletePerson(int id) {
        try {
            Connection conn = ConnexionDB.getConnection();
            if (conn == null) {
                System.out.println("Impossible de supprimer: Pas de connexion");
                return;
            }
            
            PreparedStatement ps = conn.prepareStatement("DELETE FROM persons WHERE id = ?");
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            
            System.out.println("Personne supprimée: " + result + " ligne(s) affectée(s)");
            
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression: " + e.getMessage());
            e.printStackTrace();
        }
    }
}