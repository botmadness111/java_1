package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "cat")
@Entity
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private Integer hp;
}
