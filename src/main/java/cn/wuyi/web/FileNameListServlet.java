package cn.wuyi.web;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wuyi.domain.FileBean;

/**
 * Servlet implementation class FileNameListServlet
 */
@WebServlet("/FileNameListServlet")
public class FileNameListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//定义要遍历的文件路径
		String rootPath = "E:\\BaiduNetdiskDownload\\书籍";
		// 向队列存放File
		File root = new File(rootPath);
		// 创建队列
		Queue<File> queue = new LinkedList<File>();
		// 把根节点入队
		queue.offer(root);
		// 循环的条件，如果队列不为空，一直循环，目的是：将遍历出来的文件名字，和路径放到list，并且放到request域中，在jsp中显示
		List<FileBean> list = new LinkedList<>();
		while (!queue.isEmpty()) {
			// 先获取根节点
			File file = queue.poll();
			// 获取file文件下的所有子节点
			File[] files = file.listFiles();
			// 循环遍历
			for (File f : files) {
				// 拿到每一个File对象，判断当前File是文件还是文件夹
				if (f.isFile()) {
					// 如果是一个文件，提供下载。显示到页面上。

					//自己定义了一个filebean类，封装两个值，文件的绝对路径，另一个是文件名字
					FileBean bean = new FileBean();
					bean.setFilepath(f.getCanonicalPath());
					bean.setFilename(f.getName());
					list.add(bean);
					//System.out.println("--->" + f.getCanonicalPath() + f.getName());

				} else {
					// 如果是一个文件夹
					queue.offer(f);
				}

				//将集合放到request域中
				request.setAttribute("list", list);
				//list.forEach(li->System.out.println(li));

			}
		}
		//转发到jsp页面显示
		request.getRequestDispatcher("filesDL.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
