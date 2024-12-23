package ru.prorain.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.prorain.dto.MatchDto;
import ru.prorain.entity.User;
import ru.prorain.exception.InvalidPlayerNameException;
import ru.prorain.exception.NotUniquePlayerNameException;
import ru.prorain.service.OngoingMatchService;

import java.io.IOException;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    OngoingMatchService ongoingMatchService = OngoingMatchService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("doGet");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstPlayerName = req.getParameter("firstPlayerName");
        String secondPlayerName = req.getParameter("secondPlayerName");

        if (firstPlayerName == null || secondPlayerName == null) {
            throw new InvalidPlayerNameException("Invalid player name");

        } else if (firstPlayerName.equals(secondPlayerName)) {
            throw new NotUniquePlayerNameException("Name must be unique");

        }
        MatchDto matchDto = ongoingMatchService.save(new User(firstPlayerName), new User(secondPlayerName));
        resp.sendRedirect("match-score?uuid=" + matchDto.getId());
    }
}
