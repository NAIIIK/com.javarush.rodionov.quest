package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.Quest;
import model.service.QuestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

class AnswerServletTest {
    private AnswerServlet servlet;
    private QuestService questService;
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private HttpSession session;

    @BeforeEach
    void setUp() {
        questService = Mockito.mock(QuestService.class);
        req = Mockito.mock(HttpServletRequest.class);
        resp = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);

        servlet = new AnswerServlet(questService);

        Mockito.when(req.getSession()).thenReturn(session);
        Mockito.when(req.getContextPath()).thenReturn("/quest");
    }

    @Test
    void doPostLoseAnswerSetsLoseTextInSession() throws IOException {
        Quest quest = Mockito.mock(Quest.class);

        Mockito.when(req.getParameter("answerId")).thenReturn("15");
        Mockito.when(session.getAttribute("quest")).thenReturn(quest);
        Mockito.when(questService.processAnswer(quest, 15L)).thenReturn("You lost");

        servlet.doPost(req, resp);

        Mockito.verify(session).setAttribute("loseText",  "You lost");
        Mockito.verify(resp).sendRedirect("/quest/game");
    }

}