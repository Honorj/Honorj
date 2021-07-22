package servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowFileServlet
 */
public class ShowFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//设置ContentType字段值
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			//获取所要预览的文件名称
			String filename = request.getParameter("filename");
			//预览文件所在的目录
			//String folder = "/download_file/";
			if(filename.equals("3.jpg")) {
				out.write("<img src=./download_file/3.jpg />");
				out.print(filename);
			}else{
			//在tomcat部署后目录改变，相对路径无效，所以得用getServletContext().getRealPath()来获取文件的路径
			//且getServletContext().getRealPath()只能在servlet中使用。
			FileInputStream in = new FileInputStream(this.getServletContext().getRealPath("/file/"+filename));
			InputStreamReader isr = new InputStreamReader(in);
			char[] ch = new char[1024];
			int len=0;
			while((len=isr.read(ch))!=-1) {
			out.print(new String(ch,0,len)+"<br />");
			}
			 isr.close();
			}
			  out.print("<a href='User/user_index.html'>返回主菜单</a>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
  	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
