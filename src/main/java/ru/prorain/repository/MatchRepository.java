package ru.prorain.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.prorain.entity.Match;
import ru.prorain.exception.DatabaseException;
import ru.prorain.utils.ConnectionManager;

import java.util.List;

public class MatchRepository implements CrudRepository<Match, Integer>, MatchSpecificOperation {

    private static final MatchRepository MATCH_REPOSITORY = new MatchRepository();

    private MatchRepository() {
    }

    static SessionFactory sessionFactory = ConnectionManager.getSessionFactory();

    @Override
    public Match save(Match match) {
        try (Session session = sessionFactory.getCurrentSession()
        ) {
            session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
        } catch (HibernateException e) {

        }
        return match;
    }

    @Override
    public Match update(Match match) {
        return null;
    }

    @Override
    public List<Match> getAll() {
        List<Match> list;
        String hqlQuery = "FROM Match";
        try (Session session = sessionFactory.getCurrentSession()) {
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


    public List<Match> getFilteredMatchesByPlayerName(String filterByPlayerName) {
        String hqlQuery = "FROM Match WHERE player1.name =:filterByPlayerName OR player2.name =:filterByPlayerName";
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session.createQuery(hqlQuery);
            query.setParameter("filterByPlayerName", filterByPlayerName);
            List<Match> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<Match> getFilteredBySize(int page) {

        String hqlQuery = "FROM Match";

        try (Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            Query query = session.createQuery(hqlQuery);
            query.setFirstResult((page - 1) * 10);
            query.setMaxResults(10);
            List<Match> resultList = query.setMaxResults(10).getResultList();
            session.getTransaction().commit();
            return resultList;
        } catch (HibernateException e) {

            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public Long getPagesAmount() {
        String hqlPageCountQuery = "SELECT COUNT(*) FROM Match";
        Long page;

        try (Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            Query countQuery = session.createQuery(hqlPageCountQuery);
            Long count = (Long) countQuery.uniqueResult();
            if (count % 10 != 0) {
                page = count / 10 + 1;
            } else {
                page = count / 10;
            }
            return page;
        }
    }
}
