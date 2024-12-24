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
import java.util.Objects;

@WebServlet("/matches/*")
public class MatchesServlet extends HttpServlet {

    FinishMatchPersistenceService matchService = FinishMatchPersistenceService.getInstance();
    OngoingMatchService ongoingMatchService = OngoingMatchService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Match> matchList;
        String filterByPlayerName = req.getParameter("filter_by_player_name");
        Long pages = matchService.getPagesAmount();
        String pageString =  req.getParameter("page");
        Long page;
        if(pageString != null){
             page = Long.parseLong(pageString);
        } else {
            page = 1L;
        }


        if (filterByPlayerName != null && !filterByPlayerName.isEmpty()) {
            matchList = matchService.getFilteredMatchesByPlayerName(filterByPlayerName);
        } else {
            if (page < 1L){
                page = 1L;
            } else if (page > pages){
                page = pages;
            }

            matchList = matchService.getFilteredBySize(page);
        }
        req.setAttribute("pages", pages);
        req.setAttribute("list", matchList);
        req.getRequestDispatcher("matches.jsp").forward(req, resp);

    }
}
