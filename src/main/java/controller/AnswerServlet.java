package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.Quest;
import model.service.QuestService;


import java.io.IOException;

@WebServlet(name = "AnswerServlet", value = "/answer")
public class AnswerServlet extends HttpServlet {
    private final transient QuestService questService = new QuestService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long answerId = Long.parseLong(req.getParameter("answerId"));
        Quest quest = (Quest) req.getSession().getAttribute("quest");

        String loseText = questService.processAnswer(quest, answerId);

        if (loseText != null) {
            req.getSession().setAttribute("loseText", loseText);
        }

        resp.sendRedirect(req.getContextPath() + "/game");

        // Exception handling in servlets???
    }
}