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

   public Person show(int id){
//
//       return people.stream()
//                .filter(person -> person.getId() == id)
//                .findAny()
//                .orElseThrow();
       return null;
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
//        Person personToUpdate = show(id);
//        personToUpdate.setName(person.getName());
//        personToUpdate.setEmail(person.getEmail());
//        personToUpdate.setSurname(person.getSurname());
//        personToUpdate.setAge(person.getAge());
    }

    public void delete(int id){

//        people.removeIf(p -> p.getId() == id);
    }

    private int random(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
