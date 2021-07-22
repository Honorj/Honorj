package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UseDAO;
import bean.UserBean;

/**
 * Servlet implementation class Change
 */
public class Change extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Change() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		String ID = request.getParameter("ID");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		UseDAO userdao=new UseDAO();
		UserBean user = new UserBean();
		user.setID(ID);
		user.setName(name);
		user.setSex(sex);
		user.setAddress(address);
		user.setPhone(phone);
		boolean b = userdao.update(user);
		System.out.println(b);
		out.print("<center><body bgcolor='green'>");
		out.print("<a href='admin/admin_index.html'><font size=4 color=white>返回管理菜单</font></a>");
 		out.print("</center></body>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
