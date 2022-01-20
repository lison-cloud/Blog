package by.bsuir.blog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.bsuir.blog.action.Action;
import by.bsuir.blog.action.ActionFactory;
import by.bsuir.blog.action.impl.ActionFactoryImpl;
import by.bsuir.blog.repository.AbstractBaseRepository;
import by.bsuir.blog.repository.exception.RepositoryException;

public class Controller
    extends HttpServlet {

    private ActionFactory factory;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            AbstractBaseRepository.initRepository("db.properties");
        } catch (RepositoryException e) {
            throw new ServletException("cannot init repository",e);
        }
        this.factory = ActionFactoryImpl.getInstance();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Action action = factory.getAction(request);
        String view = action.execute(request, response);
        if(!view.startsWith("/blog")) {
            request.getRequestDispatcher(view).forward(request, response);
        } else {
            response.sendRedirect(view);
        }
        
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
