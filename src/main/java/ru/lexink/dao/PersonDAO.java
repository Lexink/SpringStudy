package ru.lexink.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.concurrent.ThreadLocalRandom;
import ru.lexink.entity.Person;

import java.util.ArrayList;
import java.util.List;

//@Repository
@Component
@AllArgsConstructor
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    public List<Person> index() {
        String SQL = "SELECT * FROM person";

        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Person.class));
    }

   public Person show(int id) {
       String SQL = "SELECT * from person WHERE id=?";

       return jdbcTemplate.query(SQL, new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
               .stream().findAny().orElse(null);
   }

    public void save(Person person){

        String SQL = "INSERT INTO person VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(SQL, 9, person.getName(), person.getAge(), person.getEmail());

    }

    public void update(int id, Person person){
        String SQL = "UPDATE person SET name=?, age=?, email=? WHERE id=?";
        jdbcTemplate.update(SQL, person.getName(), person.getAge(), person.getEmail(), id);
    }

    public void delete(int id){
        String SQL = "DELETE FROM person WHERE id=?";
        jdbcTemplate.update(SQL, id);
    }

}
