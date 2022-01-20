package by.bsuir.blog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class LocaleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");

        HttpSession session = request.getSession();


        if(session.getAttribute("lang") == null) {
            session.setAttribute("lang", "en");
        }

        String locale = req.getParameter("language");

        if (locale == null) {
            StringBuilder builder = new StringBuilder(request.getRequestURL());
            String query = request.getQueryString();
            if (query != null) {
                builder.append("?").append(query);
            }
            session.setAttribute("latest", builder.toString());
            System.out.println("info: " + builder);
        } else {
            session.setAttribute("lang", locale);
            String latest = (String) session.getAttribute("latest");
            if(latest==null) {
                StringBuilder builder = new StringBuilder(request.getRequestURL());
                String query = request.getQueryString();
                if (query != null) {
                    builder.append("?").append(query);
                }
                latest=builder.toString();
            }
            ((HttpServletResponse)resp).sendRedirect(latest);
            return;
        }

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {}

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

}