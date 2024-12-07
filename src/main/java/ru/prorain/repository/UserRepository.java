package ru.prorain.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.slf4j.LoggerFactory;
import ru.prorain.entity.User;
import ru.prorain.exception.DatabaseException;
import ru.prorain.exception.EntiteExistingException;
import ru.prorain.utils.ConnectionManager;

import java.util.Collection;
import org.slf4j.Logger;


public class UserRepository implements CrudRepository<User, Integer>  {

    Logger logger = LoggerFactory.getLogger(UserRepository.class);
    SessionFactory sessionFactory = ConnectionManager.getSessionFactory();

    private static final UserRepository USER_REPOSITORY = new UserRepository();

    private UserRepository(){
    }

    @Override
    public User save(User user) {

        try (Session currentSession = sessionFactory.getCurrentSession()) {


            currentSession.beginTransaction();

            User existingUser = currentSession.createQuery("FROM User WHERE name = :name", User.class)
                    .setParameter("name", user.getName())
                    .uniqueResult();
            if (existingUser != null) {
                return existingUser;
            }

            currentSession.persist(user);
            currentSession.getTransaction().commit();
        } catch (HibernateException e) {
            if(e.getMessage().contains("Unique index or primary key violation")){
                throw new EntiteExistingException("PLayer with name: " + user.getName() + " already exists");
            };
            throw new DatabaseException("Failed to save player with name: " + user + "in database");
        }
        getOne();
        return user;
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
