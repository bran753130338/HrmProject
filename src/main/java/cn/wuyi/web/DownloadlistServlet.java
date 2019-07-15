package cn.wuyi.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wuyi.utils.DownloadUtils;


/**
 * Servlet implementation class DownloadlistServlet
 */
@WebServlet("/DownloadlistServlet")
public class DownloadlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 处理中文乱码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 传入参数可能有中文了
		String path = request.getParameter("path");
		//System.out.println("path==" + path);
		// 自己截取文件的名称
		int index = path.lastIndexOf("\\");
		// 截取
		String filename = path.substring(index + 1);
		// 编写文件的下载
		// 获取MIME的类型
		String type = getServletContext().getMimeType(filename);
		// 两个头一个流
		response.setContentType(type);

		// 先获取浏览器的信息
		String agent = request.getHeader("User-Agent");
		// Mozilla/5.0 (Windows NT 6.1; WOW64; rv:30.0) Gecko/20100101 Firefox/30.0
		// 火狐浏览器 Base64编码
		// Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)
		// IE的浏览器 使用URL编码
		// System.out.println(agent);

		// 判断是什么浏览器
		if (agent.contains("Firefox")) {
			// 使用Base64编码
			filename = DownloadUtils.base64EncodeFileName(filename);
		} else {
			// IE或者谷歌的浏览器 URL编码
			filename = URLEncoder.encode(filename, "UTF-8");
			// 把空格编码成+号
			filename = filename.replace("+", " ");
		}

		// 直接设置
		response.setHeader("Content-Disposition", "attachment;filename=" + filename);
		// 一个输入流:读取硬盘中的文件
		InputStream in = new BufferedInputStream(new FileInputStream(path));
		// 输出流
		OutputStream os = new BufferedOutputStream(response.getOutputStream());
		int len = 0;
		byte[] b = new byte[1024];
		while ((len = in.read(b)) != -1) {
			os.write(b, 0, len);
		}
		in.close();
		os.close();
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
