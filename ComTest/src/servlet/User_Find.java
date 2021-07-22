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
 * Servlet implementation class User_Find
 */
public class User_Find extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User_Find() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		UseDAO userdao=new UseDAO();
		String ID=request.getParameter("ID");
		UserBean user = userdao.findById(ID);
		out.print("<body bgcolor='green'><center>");
		if(user!=null) {
			out.print("ѧ�ţ�"+user.getID()+"&nbsp;&nbsp;������"+user.getName()+"&nbsp;&nbsp;�Ա�"+
					user.getSex()+"&nbsp;&nbsp;��ַ��"+user.getAddress()+"&nbsp;&nbsp;�绰��"+user.getPhone()+"<br />");
		}else {
			out.print("<h3>û���û�</h3>");	
		}
		out.print("<a href='User/user_index.html'><font size=4 color=white>���ع���˵�</font></a>");
		out.print("</center></body>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
