package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/app/plan/edit")
public class PlanEdit extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		int planId = Integer.parseInt(request.getParameter("plan_id"));
		String name = request.getParameter("planName");
		String description = request.getParameter("planDescription");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Admin user = (Admin) session.getAttribute("user");

		PlanDao.update(new Plan(planId, name, description, timestamp, user.getId()));
		response.sendRedirect(request.getContextPath() + "/app/plan/list");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int planId = Integer.parseInt(request.getParameter("plan_id"));
		Plan plan = PlanDao.read(planId);

		request.setAttribute("plan", plan);

		getServletContext().getRequestDispatcher("/appPlanEdit.jsp").forward(request, response);

	}
}
