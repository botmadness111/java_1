package org.example.controller;

import org.example.model.Cat;
import org.example.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CatController {

    private final CatService service;

    @Autowired
    public CatController(CatService service) {
        this.service = service;
    }

    @GetMapping("/cat")
    public List<Cat> cats() {
        return service.cats();
    }

    @GetMapping("/cat/{id}")
    public Cat cat(@PathVariable("id") int id) {
        return service.cat(id);
    }

    @PostMapping("/cat")
    public void addCat(Cat cat) {
        service.addCat(cat);
    }

    @PutMapping("/cat")
    public Cat updateCat(Cat cat) {
        return service.updateCat(cat);
    }

}
