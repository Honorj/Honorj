package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String[] m = request.getParameterValues("mold");
         String username = (String)request.getParameter("username");
         String password = (String)request.getParameter("password");
		if(m != null){
           for(int i=0;i<m.length;i++){
                if(m[i].equals("user")){                  
                    if((username).equals("���ӽ�") && (password).equals("123")){
                        response.sendRedirect("/ComTest/User/user_index.html");
                    }
                    else{
                    	out.print("�û�����������󣬵�¼ʧ�ܣ���");
                    }                       
                }
                if(m[i].equals("admin")){
                    if(username.equals("Ҧ��ʦ") && (password.equals("123"))){
                    	response.sendRedirect("/ComTest/admin/admin_index.html");
                    }
                    else{
                    	out.print("�û�����������󣬵�¼ʧ�ܣ���");	
                    }
                }
            }
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
