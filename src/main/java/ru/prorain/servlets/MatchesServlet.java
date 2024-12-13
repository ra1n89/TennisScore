package ru.prorain.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.prorain.service.MatchService;

import java.io.IOException;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

    MatchService matchService = MatchService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        resp.getWriter().println(matchService.getAll());

    }
}
