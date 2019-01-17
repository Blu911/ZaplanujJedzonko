package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.dao.PlanDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/dashboard")
public class DashboardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sess = request.getSession();
        request.setAttribute("countUserRecipes", RecipeDao.countUserRecipes(((Admin)sess.getAttribute("user")).getId()));
        request.setAttribute("countUserPlans", PlanDao.countUserPlans(((Admin)sess.getAttribute("user")).getId()));
        getServletContext().getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }
}
