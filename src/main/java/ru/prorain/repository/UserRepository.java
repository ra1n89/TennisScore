package ru.prorain.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import ru.prorain.entity.User;
import ru.prorain.utils.ConnectionManager;

import java.util.Collection;


public class UserRepository implements CrudRepository<User, Integer>{

    SessionFactory sessionFactory = ConnectionManager.getSessionFactory();

    private static final UserRepository USER_REPOSITORY = new UserRepository();

    private UserRepository(){
    }

    @Override
    public User save(User t) {

        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.beginTransaction();
        currentSession.persist(new User("Anton"));
        currentSession.getTransaction().commit();

        getOne();
        return null;
    }

    @Override
    public User update(User t) {
        return null;
    }

    @Override
    public Collection getAll() {
        return null;
    }

    @Override
    public User getOne() {
        Session currentSession = sessionFactory.openSession();

        currentSession.beginTransaction();
        User user = currentSession.get(User.class, 1);
        currentSession.getTransaction().commit();

        System.out.println(user);
        return null;
    }

    public static UserRepository getInstance(){
        return USER_REPOSITORY;
    }
}
