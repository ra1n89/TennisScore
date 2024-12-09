package ru.prorain.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.prorain.entity.Match;
import ru.prorain.utils.ConnectionManager;

import java.util.Collection;

public class MatchRepository implements CrudRepository<Match, Integer>{

    SessionFactory sessionFactory = ConnectionManager.getSessionFactory();
    @Override
    public Match save(Match match) {
        try(Session session = sessionFactory.getCurrentSession()){
           session.beginTransaction();
           session.persist(match);
           session.getTransaction().commit();
        }
        return null;
    }

    @Override
    public Match update(Match match) {
        return null;
    }

    @Override
    public Collection<Match> getAll() {
        return null;
    }

    @Override
    public Match getOne() {
        return null;
    }
}
