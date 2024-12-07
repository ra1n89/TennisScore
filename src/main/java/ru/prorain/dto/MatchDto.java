package ru.prorain.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.prorain.entity.User;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchDto {


    UUID id;
    User player1;
    User player2;
    int score1;
    int score2;

    public MatchDto(User player1, User player2, int score1, int score2) {
        id = UUID.randomUUID();
        this.player1 = player1;
        this.player2 = player2;
        this.score1 = score1;
        this.score2 = score2;
    }
}