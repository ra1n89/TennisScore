package ru.prorain.service;

import ru.prorain.dto.MatchDto;
import ru.prorain.entity.User;
import ru.prorain.repository.CrudRepository;
import ru.prorain.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class UserService {

    private static final UserService USER_SERVICE = new UserService();
    CrudRepository<User, Integer> crudRepository = UserRepository.getInstance();
    ConcurrentMap<UUID, MatchDto> concurrentHashMap = new ConcurrentHashMap<>();

    private UserService() {

    }

    public MatchDto save(User firstPlayer, User secondPlayer) {
        User firstPlayerWithId;
        User secondPlayerWithId;

            firstPlayerWithId = crudRepository.save(firstPlayer);
            secondPlayerWithId = crudRepository.save(secondPlayer);

        MatchDto match = new MatchDto(firstPlayerWithId.getId(), secondPlayerWithId.getId(),0,0 );
        concurrentHashMap.put(match.getId(), match);
        return match;
    }


    public User update(User id) {
        return null;
    }


    public Collection getAll() {
        return null;
    }

    public User getOne() {

        return null;
    }

    public MatchDto getMatch(UUID uuid){

        return concurrentHashMap.get(uuid);
    }
    public ConcurrentMap<UUID, MatchDto> getList(){
        return concurrentHashMap;
    }

    public static UserService getInstance() {
        return USER_SERVICE;
    }
}
