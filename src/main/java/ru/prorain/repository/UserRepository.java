package ru.prorain.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.prorain.entity.User;
import ru.prorain.exception.DatabaseException;
import ru.prorain.exception.EntiteExistingException;
import ru.prorain.utils.ConnectionManager;

import java.util.Collection;


public class UserRepository implements CrudRepository<User, Integer> {

    Logger log = LoggerFactory.getLogger(UserRepository.class);
    SessionFactory sessionFactory = ConnectionManager.getSessionFactory();

    private static final UserRepository USER_REPOSITORY = new UserRepository();

    private UserRepository() {
    }

    @Override
    public User save(User user) {
        log.info("Saving user in class with method save()");

        try (Session currentSession = sessionFactory.getCurrentSession()) {

            log.debug("Start saving user: {}, start open transaction", user);
            currentSession.beginTransaction();
            log.debug("Transaction opened");
            log.debug("Check if the User exist in DB");
            User existingUser = currentSession.createQuery("FROM User WHERE name = :name", User.class)
                    .setParameter("name", user.getName())
                    .uniqueResult();

            if (existingUser != null) {
                log.debug("User exists already");
                return existingUser;
            }
            log.debug("User doesnt exist, saving to DB");
            currentSession.persist(user);
            currentSession.getTransaction().commit();
            log.debug("User saved to DB successfully");
        } catch (HibernateException e) {
            log.debug("Exception");
            if (e.getMessage().contains("Unique index or primary key violation")) {
                log.debug("Unique index or primary key violation");
                throw new EntiteExistingException("PLayer with name: " + user.getName() + " already exists");
            }
            ;
            log.debug("Another exception occurred");
            throw new DatabaseException("Failed to save player with name: " + user + "in database");
        }

        return user;
    }

    @Override
    public User update(User t) {
        log.info("Updating user in class with method update()");
        return null;
    }

    @Override
    public Collection getAll() {
        log.info("Getting all users in class with method getAll()");
        return null;
    }

    @Override
    public User getOne() {
        log.info("Getting user by Id in class with method getOne()");
        Session currentSession = sessionFactory.openSession();

        currentSession.beginTransaction();
        User user = currentSession.get(User.class, 1);
        currentSession.getTransaction().commit();

        System.out.println(user);
        return null;
    }

    public static UserRepository getInstance() {

        return USER_REPOSITORY;
    }
}
