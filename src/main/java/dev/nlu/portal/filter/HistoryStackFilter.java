package dev.nlu.portal.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class HistoryStackFilter implements Filter {
    private static final int MAX_HISTORY = 5;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        if ("GET".equalsIgnoreCase(req.getMethod())) {

            Deque<String> history =
                    (Deque<String>) session.getAttribute("history");

            if (history == null) {
                history = new ArrayDeque<>();
            }

            String uri = req.getRequestURI();
            String query = req.getQueryString();
            String fullUrl = uri + (query != null ? "?" + query : "");

            if (history.isEmpty() || !history.peek().equals(fullUrl)) {
                history.push(fullUrl);
            }

            while (history.size() > MAX_HISTORY) {
                history.removeLast();
            }

            session.setAttribute("history", history);
        }

        chain.doFilter(request, response);

    }
}
