package org.example.model;

import java.util.ArrayList;
import java.util.List;

public record Person(String name, Integer age) {
    public static List<Person> people = new ArrayList<>();

    static {
        people.add(new Person("John Doe", 18));
        people.add(new Person("Maks", 20));
        people.add(new Person("Igor Kr", 14));
        people.add(new Person("Andrey Korolev ikbo-20-22", 20));
    }

    public static Person findByName(String name) {
        return people.stream().filter(person -> person.name().equals(name)).findFirst().orElse(null);
    }

    public static Person removeByName(String name) {
        Person person = findByName(name);
        people.remove(person);
        return person;
    }

    public static List<Person> getPeople() {
        return people;
    }
}
