package ru.prorain.servlets;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import ru.prorain.entity.Match;
import ru.prorain.entity.User;
import ru.prorain.utils.ConnectionManager;

@WebListener
public class ServletListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        SessionFactory sessionFactory = ConnectionManager.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();

        String playersCreateTableSql = """
        CREATE TABLE Players (
            id INT PRIMARY KEY AUTO_INCREMENT,
            name VARCHAR UNIQUE
        )
        """;

        String matchCreateTableSql = """
            CREATE TABLE matches (
            id INT PRIMARY KEY AUTO_INCREMENT,
            player1 INT,
            player2 INT,
            winner INT
            )
            """;

        currentSession.beginTransaction();
        currentSession.createNativeQuery(playersCreateTableSql).executeUpdate();
        currentSession.createNativeQuery(matchCreateTableSql).executeUpdate();
        currentSession.getTransaction().commit();
    }
}
