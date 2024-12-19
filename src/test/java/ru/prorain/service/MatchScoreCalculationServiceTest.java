package ru.prorain.service;

import org.junit.jupiter.api.Test;
import ru.prorain.dto.MatchDto;
import ru.prorain.entity.Match;
import ru.prorain.entity.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatchScoreCalculationServiceTest {
    OngoingMatchService ongoingMatchService = OngoingMatchService.getInstance();
    FinishMatchPersistenceService finishMatchPersistenceService = FinishMatchPersistenceService.getInstance();
    MatchScoreCalculationService matchScoreCalculationService = MatchScoreCalculationService.getInstance();




            //TODO: перенсти тест в сервис персистент
//    void saveToDb(){
//
//        List<Match> testMatchesList = new ArrayList<>();
//        User player1 = new User("Anton");
//        User player2 = new User("Petr");
//        User player3 = new User("Sergey");
//        User player4 = new User("Ivan");
//        matchService.save(player1, player2);
//        matchService.save(player3, player4);
//        match1 = new Match(player1, player2, player1);
//        Match match2 = new Match(player3, player4, player4);
//
//        testMatchesList.add(match1);
//        testMatchesList.add(match2);
//
//        finishMatchPersistenceService.save(match1);
//        finishMatchPersistenceService.save(match2);
//
//
//        List<Match> matchesFromDb = finishMatchPersistenceService.getAll();
//        assertEquals(testMatchesList, matchesFromDb, "Saving to DB correct" );
//    }


    @Test
    void CheckIsFirstPlayerWinAllMatches(){
        MatchDto match = ongoingMatchService.save(new User("Anton"), new User("Petr")); // Создаем матч



        for (int i = 0; i <=47; i++) {
            matchScoreCalculationService.updateScore(match.getPlayer1().getId(), match.getId());
        }

        assertAll("Score should be updated correctly when 1st player wins all matches and two sets",
                () -> assertEquals(match.getScore1(), 0, "Score doesnt match"),
                () -> assertEquals(match.getScore2(), 0, "Score doesnt match"),
                () -> assertEquals(match.getGame1(), 0, "Score doesnt match"),
                () -> assertEquals(match.getGame2(), 0, "Score doesnt match"),
                () -> assertEquals(match.getSets1(), 2, "Score doesnt match"),
                () -> assertEquals(match.getSets2(), 0, "Score doesnt match"));

        MatchDto match2 = ongoingMatchService.save(new User("Anton"), new User("Petr")); // Создаем матч

        for (int i = 0; i <=47; i++) {
            matchScoreCalculationService.updateScore(match2.getPlayer1().getId(), match2.getId());
            matchScoreCalculationService.updateScore(match2.getPlayer2().getId(), match2.getId());
            System.out.println(i + " - " + match2.getScore1() + " : " + match2.getGame1() + " : " + match2.getSets1());
            System.out.println(i + " - " + match2.getScore1() + " : " + match2.getGame1() + " : " + match2.getSets1());

        }

        assertAll("Score should be updated correctly when 1st player wins all matches and two sets",
                () -> assertEquals(match.getScore1(), 0, "Score doesnt match"),
                () -> assertEquals(match.getScore2(), 0, "Score doesnt match"),
                () -> assertEquals(match.getGame1(), 0, "Score doesnt match"),
                () -> assertEquals(match.getGame2(), 0, "Score doesnt match"),
                () -> assertEquals(match.getSets1(), 2, "Score doesnt match"),
                () -> assertEquals(match.getSets2(), 0, "Score doesnt match"));

    }

    @Test
    void CheckIfBothPlayersCantWinGame(){

        MatchDto match = ongoingMatchService.save(new User("Anton"), new User("Petr")); // Создаем матч

        for (int i = 0; i <=47; i++) {
            matchScoreCalculationService.updateScore(match.getPlayer1().getId(), match.getId());
            matchScoreCalculationService.updateScore(match.getPlayer2().getId(), match.getId());
            System.out.println(i + " - " + match.getScore1() + " : " + match.getGame1() + " : " + match.getSets1());
            System.out.println(i + " - " + match.getScore1() + " : " + match.getGame1() + " : " + match.getSets1());

        }

        assertAll("Score should be updated correctly when 1st player wins all matches and two sets",
                () -> assertEquals(match.getScore1(), 490, "Score doesnt match"),
                () -> assertEquals(match.getScore2(), 490, "Score doesnt match"));
    }

    @Test
    void CheckIfBothPlayersCantWinTaybreak(){

        MatchDto match = ongoingMatchService.save(new User("Anton"), new User("Petr")); // Создаем матч

        match.setGame1(6);
        match.setGame2(6);
        match.setScore1(40);
        match.setScore2(0);


        for (int i = 0; i <=47; i++) {

            if (match.getGame1() > match.getGame2()){
                match.setScore1(30);
                match.setScore2(40);
                matchScoreCalculationService.updateScore(match.getPlayer2().getId(), match.getId());
                System.out.println(1 + " - " + match.getScore1() + " : " + match.getGame1() + " : " + match.getSets1());
                System.out.println(1 + " - " + match.getScore2() + " : " + match.getGame2() + " : " + match.getSets2());
            } else if (match.getGame1() < match.getGame2()){
                match.setScore1(40);
                match.setScore2(30);
                matchScoreCalculationService.updateScore(match.getPlayer1().getId(), match.getId());
                System.out.println(2 + " - " + match.getScore1() + " : " + match.getGame1() + " : " + match.getSets1());
                System.out.println(2 + " - " + match.getScore2() + " : " + match.getGame2() + " : " + match.getSets2());
            } else {

                match.setScore1(40);
                matchScoreCalculationService.updateScore(match.getPlayer1().getId(), match.getId());
                System.out.println(3 + " - " + match.getScore1() + " : " + match.getGame1() + " : " + match.getSets1());
                System.out.println(3 + " - " + match.getScore2() + " : " + match.getGame2() + " : " + match.getSets2());
            }


        }

        assertAll("Score should be updated correctly when both players play taybreak and cant win",
                () -> assertEquals(match.getScore1(), 0, "Score doesnt match"),
                () -> assertEquals(match.getScore2(), 0, "Score doesnt match"),
                () -> assertEquals(match.getGame1(), 30, "Score doesnt match"),
                () -> assertEquals(match.getGame2(), 30, "Score doesnt match"),
                () -> assertEquals(match.getSets1(), 0, "Score doesnt match"),
                () -> assertEquals(match.getSets2(), 0, "Score doesnt match"));
    }




}