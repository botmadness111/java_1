package org.example.service;

import jakarta.transaction.Transactional;
import org.example.model.Cat;
import org.example.repository.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatService {
    private final CatRepository repository;

    @Autowired
    public CatService(CatRepository catRepository) {
        this.repository = catRepository;
    }

    public List<Cat> cats() {
        return repository.findAll();
    }

    public Cat cat(int id) {
        return repository.findById(id).orElseThrow();
    }

    public void addCat(Cat cat) {
        repository.save(cat);
    }

    @Transactional
    public Cat updateCat(Cat cat) {
        Cat cat1 = repository.findById(cat.getId()).orElseThrow();

        cat1.setName(cat.getName());
        cat1.setHp(cat.getHp());
        cat1.setAge(cat.getAge());

        return repository.save(cat1);
    }

}
