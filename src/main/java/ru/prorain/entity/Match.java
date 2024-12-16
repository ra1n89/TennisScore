package ru.prorain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "player1", referencedColumnName = "id")
    private User player1;
    @JoinColumn(name = "player2", referencedColumnName = "id")
    @OneToOne
    private User player2;
    @JoinColumn(name = "winner", referencedColumnName = "id")
    @OneToOne
    private User winner;

    //конструктор без одного аргумента - id
    public Match(User player1, User player2, User winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }
}
