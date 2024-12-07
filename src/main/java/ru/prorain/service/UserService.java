package ru.prorain.service;

import ru.prorain.entity.User;
import ru.prorain.repository.CrudRepository;
import ru.prorain.repository.UserRepository;

import java.util.Collection;

public class UserService {

    private static final UserService USER_SERVICE = new UserService();
    CrudRepository<User, Integer> crudRepository = UserRepository.getInstance();


    private UserService() {

    }

    public User save(User firstPlayer, User secondPlayer) {
        try {
            crudRepository.save(firstPlayer);
        } catch (Exception e) {
            e.getMessage();
        }
        try {
            crudRepository.save(secondPlayer);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
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

    public static UserService getInstance() {
        return USER_SERVICE;
    }
}
