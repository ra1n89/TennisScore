package ru.prorain.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import ru.prorain.entity.User;

import java.util.Collection;


public class UserRepository implements CrudRepository<User, Integer>{
    SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).buildSessionFactory();

    public UserRepository(){
        String sql = """
        CREATE TABLE Players (
            id INT PRIMARY KEY AUTO_INCREMENT,
            name VARCHAR UNIQUE
        )
        """;
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        NativeQuery<User> query = currentSession.createNativeQuery(sql).addEntity(User.class);
        query.executeUpdate();
        currentSession.getTransaction().commit();

    }


    @Override
    public User save(User id) {




        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        //NativeQuery<User> query = currentSession.createNativeQuery(sql).addEntity(User.class);

        currentSession.persist(new User("Anton"));
        currentSession.getTransaction().commit();
        getOne();
        return null;
    }

    @Override
    public User update(User id) {
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
}
