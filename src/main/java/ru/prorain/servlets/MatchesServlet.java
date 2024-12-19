package ru.prorain.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.prorain.entity.Match;
import ru.prorain.service.FinishMatchPersistenceService;
import ru.prorain.service.OngoingMatchService;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

    FinishMatchPersistenceService matchService = FinishMatchPersistenceService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Match> matchList = matchService.getAll();
        req.setAttribute("list", matchList);
        req.getRequestDispatcher("/matches.jsp").forward(req, resp);


    }
}
