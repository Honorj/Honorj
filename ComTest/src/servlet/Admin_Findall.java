package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UseDAO;
import bean.UserBean;

/**
 * Servlet implementation class Admin_Findadd
 */
public class Admin_Findall extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_Findall() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		UseDAO usedao = new UseDAO();
		ArrayList list = usedao.findAll();
		out.print("<body bgcolor='green'><center>");
		for (int i = 0; i < list.size(); i++) {
			UserBean obj=(UserBean)list.get(i);
			out.write("学号："+obj.getID()+"&nbsp;&nbsp;姓名："+obj.getName()+"&nbsp;&nbsp;性别："+
					obj.getSex()+"&nbsp;&nbsp;地址："+obj.getAddress()+"&nbsp;&nbsp;电话："+obj.getPhone()+"<br><br>");
		}
		
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
