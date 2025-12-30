package com.poly;

import java.util.List;

public interface PersonService {

    List<Person> getAllPersons();
    Person getPerson(int id);
    Person getPersonByName(String name);
    void addPerson(Person person);
    void updatePerson(Person person);
    void deletePerson(int id);
}
