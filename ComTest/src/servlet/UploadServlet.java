package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadServlet
 */
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//����ContentType����
			response.setContentType("text/html;charset=utf-8");
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//�����ļ������Ŀ¼����������ھ��½�һ��
			File f = new File("D:\\Java_web����\\file");
			if(!f.exists()){
				f.mkdir();
			}
			//�����ļ������·��
			factory.setRepository(f);
			//����ServletFileUpload����
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			//�����ַ�����
			fileUpload.setHeaderEncoding("utf-8");
			List<FileItem> fileitems = fileUpload.parseRequest(request);
			//��ȡ�ַ���
			PrintWriter writer = response.getWriter(); 
			//����list����
			for(FileItem fileitem : fileitems){
				//��ȡ�ϴ��ļ���
				String filename = fileitem.getName();
				if(filename != null && !filename.equals("")){
					//��ȡ���ļ���
					filename = filename.substring(filename.lastIndexOf("\\")+1);
					//�ļ�����ҪΨһ
					//filename = UUID.randomUUID().toString()+"_"+filename;
					//�ڷ���������ͬ���ļ�
					String webPath = "/file/";
					//�����������ļ���·�����ļ�����ϳ������ķ�������·��
					String filepath = getServletContext().getRealPath(webPath+filename);
					//�����ļ�
					File file = new File(filepath);
					file.getParentFile().mkdirs();
					file.createNewFile();
					//����ϴ���������
					InputStream in = fileitem.getInputStream();
					//ʹ��FileOutputStream�򿪷������˵��ϴ��ļ�
					FileOutputStream out = new FileOutputStream(file);
					//�������ĶԿ�
					byte[] buffer = new byte[1024];//ÿ�ζ�һ���ֽ�
					int len;
					//��ʼ��ȡ�ϴ��ļ����ֽڣ�������������������˵��ϴ��ļ��������
					while((len = in.read(buffer))>0){
						out.write(buffer,0,len);
					}
					//�ر�������
					in.close();
					out.close();
					//�����ʱ�ļ�
					//fileitem.delete();
					writer.print("�ϴ�"+filename+"�ļ��ɹ���");
					writer.print("<a href='User/user_index.html'>�������˵�</a>");
				}
			}
			
		} catch (FileUploadException e) {
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
