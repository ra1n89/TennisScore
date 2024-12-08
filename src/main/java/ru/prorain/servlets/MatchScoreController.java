package ru.prorain.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.prorain.dto.MatchDto;
import ru.prorain.service.UserService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score/*")
public class MatchScoreController extends HttpServlet {
    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        MatchDto match = userService.getMatch(uuid);
        req.setAttribute("match", match);
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        userService.updateScore(id, uuid);
        MatchDto match = userService.getMatch(uuid);
        req.setAttribute("match", match);
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }
}
