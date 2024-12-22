package ru.prorain.service;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.prorain.entity.Match;
import ru.prorain.repository.MatchRepository;

import java.util.List;

public class FinishMatchPersistenceService {

    MatchRepository matchRepository = MatchRepository.getInstance();
    public static final FinishMatchPersistenceService finishMatchPersistenceService = new FinishMatchPersistenceService();
    private FinishMatchPersistenceService( ) {

    }

    public Match save(Match match) {
        matchRepository.save(match);
        return null;
    }


    public Match update(Match match) {

        matchRepository.update(match);

        return null;
    }


    public List<Match> getAll() {

        return matchRepository.getAll();
    }


    public Match getOne() {
        return null;
    }


    public static FinishMatchPersistenceService getInstance() {
        return finishMatchPersistenceService;
    }

    public List<Match> getFilteredMatchesByPlayerName(String filterByPlayerName) {
       return matchRepository.getFilteredMatchesByPlayerName(filterByPlayerName);
    }
}
