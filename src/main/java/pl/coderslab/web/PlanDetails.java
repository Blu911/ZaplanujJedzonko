package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;
import pl.coderslab.model.PlanWithMeals;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/plan/details")
public class PlanDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int planId = Integer.parseInt(request.getParameter("plan_id"));
        Plan plan = PlanDao.read(planId);

        request.setAttribute("plan", plan);

        HttpSession session = request.getSession();
        int userId = ((Admin) session.getAttribute("user")).getId();

        List<PlanWithMeals> planWithMealsList = PlanDao.getAllPlanWithMealsByAdminId(userId);

        request.setAttribute("planWithMealsList", planWithMealsList);


        request.setAttribute("planWithDetails", PlanDao.getPlanWithDetails(userId, planId));

        getServletContext().getRequestDispatcher("/appPlanDetails2.jsp").forward(request, response);
    }
}
