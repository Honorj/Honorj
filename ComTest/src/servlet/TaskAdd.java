package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.TaskBean;

import DAO.TaskDAO;

/**
 * Servlet implementation class TaskAdd
 */
public class TaskAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String newtask=request.getParameter("newtask");
		String task = request.getParameter("task");
		String time = request.getParameter("Time");
		
		TaskDAO taskdao=new TaskDAO();
		TaskBean taskbean = new TaskBean();
		taskbean.setRoot(newtask);
		taskbean.setName(task);
		taskbean.setTime(time);
		boolean b=taskdao.insert(taskbean);
		System.out.println(b);
		request.getRequestDispatcher("User/task.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
