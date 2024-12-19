package ru.prorain.service;

import ru.prorain.dto.MatchDto;
import ru.prorain.entity.Match;
import ru.prorain.entity.User;
import ru.prorain.repository.CrudRepository;
import ru.prorain.repository.MatchRepository;
import ru.prorain.repository.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class OngoingMatchService {


    MatchRepository matchRepository = MatchRepository.getInstance();
    static public ConcurrentMap<UUID, MatchDto> concurrentHashMap = new ConcurrentHashMap<>();

    CrudRepository<User, Integer> userRepository = UserRepository.getInstance();

    private static final OngoingMatchService MATCH_SERVICE = new OngoingMatchService();

    private OngoingMatchService() {

    }


    public MatchDto save(User firstPlayer, User secondPlayer) {
        User firstPlayerWithId;
        User secondPlayerWithId;

        firstPlayerWithId = userRepository.save(firstPlayer);
        secondPlayerWithId = userRepository.save(secondPlayer);

        MatchDto match = new MatchDto(firstPlayerWithId, secondPlayerWithId, 0, 0, 0, 0, 0, 0);
        OngoingMatchService.concurrentHashMap.put(match.getId(), match);
        return match;
    }


    public MatchDto getOne(UUID uuid) {
        return concurrentHashMap.get(uuid);
    }

      public static OngoingMatchService getInstance() {
        return MATCH_SERVICE;
    }


}

