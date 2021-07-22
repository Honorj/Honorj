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
			//����ContentType�ֶ�ֵ
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			//��ȡ��ҪԤ�����ļ�����
			String filename = request.getParameter("filename");
			//Ԥ���ļ����ڵ�Ŀ¼
			//String folder = "/download_file/";
			if(filename.equals("3.jpg")) {
				out.write("<img src=./download_file/3.jpg />");
				out.print(filename);
			}else{
			//��tomcat�����Ŀ¼�ı䣬���·����Ч�����Ե���getServletContext().getRealPath()����ȡ�ļ���·��
			//��getServletContext().getRealPath()ֻ����servlet��ʹ�á�
			FileInputStream in = new FileInputStream(this.getServletContext().getRealPath("/file/"+filename));
			InputStreamReader isr = new InputStreamReader(in);
			char[] ch = new char[1024];
			int len=0;
			while((len=isr.read(ch))!=-1) {
			out.print(new String(ch,0,len)+"<br />");
			}
			 isr.close();
			}
			  out.print("<a href='User/user_index.html'>�������˵�</a>");
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
