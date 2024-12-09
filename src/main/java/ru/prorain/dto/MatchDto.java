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
    int sets1;
    int sets2;
    int score1;
    int score2;
    int game1;
    int game2;
    boolean isFinish;

    public MatchDto(User player1, User player2, int score1, int score2, int sets1, int sets2, int game1, int game2) {
        id = UUID.randomUUID();
        this.player1 = player1;
        this.player2 = player2;
        this.score1 = score1;
        this.score2 = score2;
        this.sets1 = sets1;
        this.sets2 = sets2;
        this.game1 = game1;
        this.game2 = game2;
        isFinish = false;
    }

    public void setIsFinish() {
        isFinish = true;
    }
}