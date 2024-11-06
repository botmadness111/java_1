package org.example.controller;

import org.example.model.Person;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    @GetMapping("/person/{name}")
    public Person getPersons(@PathVariable("name") String name) {
        return Person.findByName(name);
    }

    @GetMapping("/person")
    public List<Person> getPersons() {
        return Person.getPeople();
    }

    @DeleteMapping("/person/{name}")
    public Person deletePerson(@PathVariable("name") String name) {
        return Person.removeByName(name);
    }
}
