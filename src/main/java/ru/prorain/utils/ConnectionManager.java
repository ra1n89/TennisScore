package ru.prorain.utils;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.prorain.entity.User;

public class ConnectionManager {
SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).buildSessionFactory();
}
