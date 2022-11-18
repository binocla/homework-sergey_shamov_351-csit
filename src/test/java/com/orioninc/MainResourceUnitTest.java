package com.orioninc;

import com.orioninc.models.Person;
import com.orioninc.resources.MainResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainResourceUnitTest {
    private static final Map<Long, Person> people = new LinkedHashMap<>();
    private static final MainResource mainResource = new MainResource();

    private static Long id = 1L;

    @BeforeAll
    public static void init() {
        people.put(1L, new Person("Sergey", 20));
    }

    @Test
    @DisplayName("Get all people")
    void myATest() {
        List<Person> list = mainResource.getAllPeople();
        assertEquals(Collections.emptyList(), list);
    }


    @Test
    @DisplayName("Get person by id")
    void myBTest() {
        Person person = new Person("Sergey", 20);
        id = mainResource.addPerson(person);
        Assertions.assertEquals(people.get(id), person);
    }

    @Test
    @DisplayName("Add person")
    void myCTest() {
        Person person = mainResource.getPersonById(id);
        Assertions.assertEquals(people.get(id), person);
    }

    @Test
    @DisplayName("Update person")
    void myDTest() {
        Person person = new Person("Sergey Shamov", 22);
        people.put(id, person);
        mainResource.updatePerson(id, person);
        Assertions.assertEquals(people.get(id), person);
    }

    @Test
    @DisplayName("Delete person")
    void myETest() {
        Person deletedPerson = mainResource.deletePerson(id);
        Assertions.assertEquals(people.get(id), deletedPerson);
    }
}