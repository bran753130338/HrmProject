package cn.wuyi.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 1.创建磁盘文件项工厂类 DiskFileItemFactory
		 * 
		 * 2.创建核心解析request类 ServletFileUpload
		 * 
		 * 3.开始解析request对象，返回是List集合
		 * 
		 * 4.List集合中保存（表单的内容，分部分）
		 * 
		 * 5.循环遍历集合，获取内容
		 */
		// 创建磁盘文件项工厂类
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 创建核心解析对象
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置上传文件的文件名称的编码
		upload.setHeaderEncoding("UTF-8");

		try {
			// 解析request
			List<FileItem> list = upload.parseRequest(request);

			if(list.isEmpty()){
                System.out.println("没有文件");
                response.getWriter().append("0");
                return;
            }
			// 循环遍历
			for (FileItem fileItem : list) {
				// fileItem有可能是普通的文本项，也有可能是一个文件上传项
				// 先判断当前fileItem是普通还是上传项
				// isFormField() 返回true，就是普通文本项，返回false，就是文件上传项
				if (fileItem.isFormField()) {
					// 普通项
					// 获取name的名称 filedesc password sex
					String name = fileItem.getFieldName();
					// 获取用户输入的值
					String value = fileItem.getString("UTF-8");
					System.out.println(name + " : " + value);
				} else {
					// 文件上传项
					// 先获取文件的名称
					String filename = fileItem.getName();
					// 你应该在数据库中把文件的原名称和UUID的名称都需要保存到数据库中。
					// 获取唯一的字符串:通用唯一识别码
					filename = UUID.randomUUID().toString() + "_" + filename;
					System.out.println("文件名称：" + filename);
					// 获取文件的输入流
					InputStream in = new BufferedInputStream(fileItem.getInputStream());
					// 项某个文件中写入
					// 项WebRoot/upload目录写入
					String path = "E:\\BaiduNetdiskDownload\\书籍";
					System.out.println("保存的路径："+path);
					// 获取输出流
					OutputStream os = new BufferedOutputStream(new FileOutputStream(path + "/" + filename));
					// io拷贝
					int len = 0;
					byte[] b = new byte[1024];
					while ((len = in.read(b)) != -1) {
						os.write(b, 0, len);
					}
					in.close();
					os.close();
					// 删除临时文件（放流关闭后执行）:
					// 用来清空FileItem类对象中存放的主体内容，如果主体内容被保存在临时文件中，delete方法将删除该临时文件。
					// 管当FileItem对象被垃圾收集器收集时会自动清除临时文件，但及时调用delete方法可以更早的清除临时文件，释放系统存储资源。另外，当系统出现异常时，仍有可能造成有的临时文件被永久保存在了硬盘中
					fileItem.delete();
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		//request.getRequestDispatcher("uploadFile.jsp").forward(request, response);
        response.getWriter().append("1");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
