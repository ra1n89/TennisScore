package ru.prorain.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.prorain.dto.MatchDto;
import ru.prorain.entity.Match;
import ru.prorain.entity.User;
import ru.prorain.repository.CrudRepository;
import ru.prorain.repository.MatchRepository;
import ru.prorain.repository.UserRepository;
import ru.prorain.utils.ConnectionManager;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MatchService {


    MatchRepository matchRepository = MatchRepository.getInstance();

    private static final MatchService MATCH_SERVICE = new MatchService();

    private MatchService() {

    }


    public Match save(Match match) {
        matchRepository.save(match);
        return null;
    }


    public Match update(Match match) {

        matchRepository.update(match);

        return null;
    }


    public Collection<Match> getAll() {

        return matchRepository.getAll();
    }


    public Match getOne() {
        return null;
    }

      public static MatchService getInstance() {
        return MATCH_SERVICE;
    }


}

