package ru.prorain.service;

import ru.prorain.dto.MatchDto;
import ru.prorain.entity.Match;
import ru.prorain.repository.MatchRepository;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class OngoingMatchService {


    MatchRepository matchRepository = MatchRepository.getInstance();
    static public ConcurrentMap<UUID, MatchDto> concurrentHashMap = new ConcurrentHashMap<>();

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

