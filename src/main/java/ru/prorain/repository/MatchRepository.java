package ru.prorain.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.processing.HQL;
import org.hibernate.query.Query;
import ru.prorain.entity.Match;
import ru.prorain.utils.ConnectionManager;

import java.util.Collection;
import java.util.List;

public class MatchRepository implements CrudRepository<Match, Integer>, MatchSpecificOperation{

    private static final MatchRepository MATCH_REPOSITORY = new MatchRepository();

    private MatchRepository() {
    }

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
    public List<Match> getAll() {
        List<Match> list;
        String hqlQuery = "FROM Match";
        try(Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            Query query = session.createQuery(hqlQuery, Match.class);
            list = query.list();
            session.getTransaction().commit();

        }

        System.out.println((list));
        return list;
    }

    @Override
    public Match getOne() {
        return null;
    }

    public static MatchRepository getInstance() {

        return MATCH_REPOSITORY;
    }

    @Override
    public List<Match> getFilteredMatchesByPlayerName(String filterByPlayerName) {
        String HQL = "FROM Match WHERE player1.name =:filterByPlayerName OR player2.name =:filterByPlayerName";
        try(Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            Query query = session.createQuery(HQL);
            query.setParameter("filterByPlayerName", filterByPlayerName);
            List<Match> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    return null;
    }
}
