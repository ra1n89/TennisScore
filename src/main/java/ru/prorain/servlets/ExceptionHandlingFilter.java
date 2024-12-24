package ru.prorain.servlets;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.prorain.exception.DatabaseException;
import ru.prorain.exception.InvalidPlayerNameException;
import ru.prorain.exception.NotUniquePlayerNameException;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.*;

@WebFilter("/*")
public class ExceptionHandlingFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        try {
            super.doFilter(req, res, chain);
        } catch (DatabaseException e) {
            writeErrorResponse(res, SC_INTERNAL_SERVER_ERROR, e);
        } catch (InvalidPlayerNameException e) {
        writeErrorResponse(res, SC_BAD_REQUEST, e);
        } catch (NotUniquePlayerNameException e) {
            writeErrorResponse(res, SC_CONFLICT, e);
        }
    }

    private void writeErrorResponse(HttpServletResponse res, int errorCode, RuntimeException e) throws IOException {
        res.setStatus(errorCode);
        res.getWriter().println(e.getMessage());
    }
}
