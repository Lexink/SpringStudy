package ru.lexink.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.lexink.entity.Person;

import java.util.ArrayList;
import java.util.List;

//@Repository
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        int limit = PEOPLE_COUNT + 10;
        people = new ArrayList<>();
        for (int i = PEOPLE_COUNT; i < limit; i++, PEOPLE_COUNT++) {
            Person person = new Person();
            person.setId(i);
            person.setName("User_" + i);
            people.add(person);
        }
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id){

       return people.stream()
                .filter(person -> person.getId() == id)
                .findAny()
                .orElseThrow();
    }

    public void save(Person person){
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person person){
        Person personToUpdate = show(id);
        personToUpdate.setName(person.getName());
        personToUpdate.setEmail(person.getEmail());
        personToUpdate.setSurname(person.getSurname());
    }

    public void delete(int id){
        people.removeIf(p -> p.getId() == id);
    }
}
