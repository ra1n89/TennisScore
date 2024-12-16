package ru.prorain.service;

import ru.prorain.entity.Match;
import ru.prorain.repository.MatchRepository;

import java.util.List;

public class OngoingMatchService {


    MatchRepository matchRepository = MatchRepository.getInstance();

    private static final OngoingMatchService MATCH_SERVICE = new OngoingMatchService();

    private OngoingMatchService() {

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

      public static OngoingMatchService getInstance() {
        return MATCH_SERVICE;
    }


}

