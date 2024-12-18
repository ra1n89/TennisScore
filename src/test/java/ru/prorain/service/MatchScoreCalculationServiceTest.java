package ru.prorain.service;

import org.junit.jupiter.api.Test;
import ru.prorain.entity.Match;
import ru.prorain.entity.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatchScoreCalculationServiceTest {

    @Test
    void test(){
        OngoingMatchService matchService = OngoingMatchService.getInstance();
        MatchScoreCalculationService matchScoreCalculationService = MatchScoreCalculationService.getInstance();
        List<Match> testMatchesList = new ArrayList<>();
        User player1 = new User("Anton");
        User player2 = new User("Petr");
        User player3 = new User("Sergey");
        User player4 = new User("Ivan");
        matchScoreCalculationService.save(player1, player2);
        matchScoreCalculationService.save(player3, player4);
        Match match1 = new Match(player1, player2, player1);
        Match match2 = new Match(player3, player4, player4);

        testMatchesList.add(match1);
        testMatchesList.add(match2);

        matchService.save(match1);
        matchService.save(match2);


        List<Match> matchesFromDb = matchService.getAll();
        assertEquals(testMatchesList, matchesFromDb, "Saving to DB correct" );
    }

}