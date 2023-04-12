package ru.lexink.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.concurrent.ThreadLocalRandom;
import ru.lexink.entity.Person;

import java.util.ArrayList;
import java.util.List;

//@Repository
@Component
public class PersonDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "admin";
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Person> index() {
        List<Person> people = new ArrayList<>();

        try{
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()){
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }

   public Person show(int id) {
       Person person = null;
       String SQL = "SELECT * from person WHERE id=?";
       try {
           PreparedStatement statement = connection.prepareStatement(SQL);
           statement.setInt(1, id);

           ResultSet resultSet = statement.executeQuery();
           resultSet.next();

           person = new Person();
           person.setId(resultSet.getInt("id"));
           person.setName(resultSet.getString("name"));
           person.setAge(resultSet.getInt("age"));
           person.setEmail(resultSet.getString("email"));
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
       return person;
   }

    public void save(Person person){
        try{
            String SQL = "INSERT INTO person VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setInt(1,7);
            statement.setString(2, person.getName());
            statement.setInt(3, person.getAge());
            statement.setString(4, person.getEmail());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Person person){
        String SQL = "UPDATE person SET name=?, age=?, email=? WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setString(3, person.getEmail());
            statement.setInt(4,id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id){
        String SQL = "DELETE FROM person WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
