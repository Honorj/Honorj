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
			//设置ContentType编码
			response.setContentType("text/html;charset=utf-8");
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//设置文件缓存的目录，如果不存在就新建一个
			File f = new File("D:\\Java_web代码\\file");
			if(!f.exists()){
				f.mkdir();
			}
			//设置文件保存的路径
			factory.setRepository(f);
			//创建ServletFileUpload对象
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			//设置字符编码
			fileUpload.setHeaderEncoding("utf-8");
			List<FileItem> fileitems = fileUpload.parseRequest(request);
			//获取字符流
			PrintWriter writer = response.getWriter(); 
			//遍历list集合
			for(FileItem fileitem : fileitems){
				//获取上传文件名
				String filename = fileitem.getName();
				if(filename != null && !filename.equals("")){
					//截取出文件名
					filename = filename.substring(filename.lastIndexOf("\\")+1);
					//文件名需要唯一
					//filename = UUID.randomUUID().toString()+"_"+filename;
					//在服务器创建同名文件
					String webPath = "/file/";
					//将服务器中文件夹路径与文件名组合成完整的服务器端路径
					String filepath = getServletContext().getRealPath(webPath+filename);
					//创建文件
					File file = new File(filepath);
					file.getParentFile().mkdirs();
					file.createNewFile();
					//获得上传的数据流
					InputStream in = fileitem.getInputStream();
					//使用FileOutputStream打开服务器端的上传文件
					FileOutputStream out = new FileOutputStream(file);
					//数据流的对拷
					byte[] buffer = new byte[1024];//每次读一个字节
					int len;
					//开始读取上传文件的字节，并将其输出到服务器端的上传文件输出流中
					while((len = in.read(buffer))>0){
						out.write(buffer,0,len);
					}
					//关闭数据流
					in.close();
					out.close();
					//输出临时文件
					//fileitem.delete();
					writer.print("上传"+filename+"文件成功！");
					writer.print("<a href='User/user_index.html'>返回主菜单</a>");
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
