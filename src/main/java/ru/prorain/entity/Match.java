package ru.prorain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int player1;
    int player2;
    int winner;

    //конструктор без одного аргумента - id
    public Match(int player1, int player2, int winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }
}
