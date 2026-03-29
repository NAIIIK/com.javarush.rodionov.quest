package controller;

import config.AppContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.Quest;
import model.service.QuestService;


import java.io.IOException;

@WebServlet(name = "AnswerServlet", value = "/answer")
public class AnswerServlet extends HttpServlet {
    private transient QuestService questService;

    //for tomcat
    public AnswerServlet() {}

    // for tests
    AnswerServlet(QuestService questService) {
        this.questService = questService;
    }

    @Override
    public void init() {
        AppContext context = (AppContext) getServletContext().getAttribute("appContext");
        this.questService = context.getQuestService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long answerId = Long.parseLong(req.getParameter("answerId"));
        Quest quest = (Quest) req.getSession().getAttribute("quest");

        String loseText = questService.processAnswer(quest, answerId);

        if (loseText != null) {
            req.getSession().setAttribute("loseText", loseText);
        }

        resp.sendRedirect(req.getContextPath() + "/game");
    }
}