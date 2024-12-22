package ru.prorain.utils;

import lombok.experimental.UtilityClass;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.prorain.entity.Match;
import ru.prorain.entity.User;
import ru.prorain.exception.DatabaseException;

@UtilityClass
public class ConnectionManager {

    private SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(User.class)
                        .addAnnotatedClass(Match.class)
                        .buildSessionFactory();
                return sessionFactory;
            } catch (HibernateException e){
                throw new DatabaseException("Error in Session Factory initialization");
            }
        }
        return sessionFactory;
    }
}
