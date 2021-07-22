package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadServlet
 */
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置ContentType字段值
		response.setContentType("text/html;charset=utf-8");
		//获取所要下载的文件名称
		String filename = request.getParameter("filename");
		//下载文件所在的目录
		String folder ="/download_file/";
		//通知浏览器以下载的方式打开
		response.addHeader("Content-Type", "application/octet-stream");
		response.addHeader("Content-Disposition", "attachment;filename="+new String(filename.getBytes("UTF-8"), "ISO8859-1"));
		//通过文件流读取文件
		InputStream in = getServletContext().getResourceAsStream(folder+filename);
		//获取response对象的输出流
		ServletOutputStream out = response.getOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		//循环取出数据
		while((len = in.read(buffer)) != -1){
			out.write(buffer,0, len);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
