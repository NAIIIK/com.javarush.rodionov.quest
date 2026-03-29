package controller;

import config.AppContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.service.QuestService;
import util.Constants;

import java.io.IOException;

@WebServlet(name = "StartServlet", value = "/start")
public class StartServlet extends HttpServlet {
    private transient QuestService questService;

    @Override
    public void init() {
        AppContext context = (AppContext) getServletContext().getAttribute("appContext");
        this.questService = context.getQuestService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nickname = (String)  req.getSession().getAttribute(Constants.NICKNAME_ATTRIBUTE);

        if (nickname != null) {
            startNewQuest(req);
            resp.sendRedirect(req.getContextPath() + "/game");
        } else {
            req.getRequestDispatcher(Constants.START_VIEW).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String nickname = req.getParameter(Constants.NICKNAME_ATTRIBUTE);

        if (nickname == null || nickname.isBlank()) {
            resp.sendRedirect(req.getContextPath() + "/start");
            return;
        }

        req.getSession().setAttribute(Constants.NICKNAME_ATTRIBUTE, nickname);

        startNewQuest(req);

        resp.sendRedirect(req.getContextPath() + "/game");
    }

    private void startNewQuest(HttpServletRequest req) {
        req.getSession().setAttribute("quest", questService.createNewQuest());
        req.getSession().removeAttribute("loseText");
    }
}
