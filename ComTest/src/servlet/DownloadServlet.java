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
		//����ContentType�ֶ�ֵ
		response.setContentType("text/html;charset=utf-8");
		//��ȡ��Ҫ���ص��ļ�����
		String filename = request.getParameter("filename");
		//�����ļ����ڵ�Ŀ¼
		String folder ="/download_file/";
		//֪ͨ����������صķ�ʽ��
		response.addHeader("Content-Type", "application/octet-stream");
		response.addHeader("Content-Disposition", "attachment;filename="+new String(filename.getBytes("UTF-8"), "ISO8859-1"));
		//ͨ���ļ�����ȡ�ļ�
		InputStream in = getServletContext().getResourceAsStream(folder+filename);
		//��ȡresponse����������
		ServletOutputStream out = response.getOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		//ѭ��ȡ������
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
