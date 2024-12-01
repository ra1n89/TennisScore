package ru.prorain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data

@Table(name = "Players")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    // Конструктор с одним аргументом
    public User(String name) {
        this.id = null;
        this.name = name; // Инициализация поля name значением null
    }
}
