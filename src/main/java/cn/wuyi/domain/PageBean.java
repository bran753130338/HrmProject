package cn.wuyi.domain;

import java.util.List;

/**
 * PageBean:将页面要显示所要用到的全部变量封装到这个类中
 * 
 * @author newuser
 *
 */
public class PageBean {
	private List<?> list;// 表格中显示的list集合
	private int totalrecord;// 总记录数量
	private int pagesize;// 一页显示多少条数据
	private int totalpage;// 一共有多少页
	private int currentpage;// 当前页
	private int previouspage;// 上一页
	private int nextpage;// 下一页
	private int[] pagebar;// 页码条

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public int getTotalrecord() {
		return totalrecord;
	}

	public void setTotalrecord(int totalrecord) {
		this.totalrecord = totalrecord;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	//总页数是计算出来的
	public int getTotalpage() {
		// 100 5 20
		// 101 5 21
		// 99 5 20

		//总页数=总记录数/每页显示条数
		//注意要判断是否可以除尽，如果有余数，则要多一页
		if (this.totalrecord % this.pagesize == 0) {
			this.totalpage = this.totalrecord / this.pagesize;
		} else {
			this.totalpage = this.totalrecord / this.pagesize + 1;
		}
		return totalpage;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	//上一页，计算出来的：上一页=当前页-1
	//注意：要判断是否是第一页
	public int getPreviouspage() {
		if (this.currentpage - 1 < 1) {
			this.previouspage = 1;
		} else {
			this.previouspage = this.currentpage - 1;
		}
		return previouspage;
	}
	//上一页，计算出来的：上一页=当前页+1
	//注意：要判断是否是最后一页
	public int getNextpage() {
		if (this.currentpage + 1 >= this.totalpage) {
			this.nextpage = this.totalpage;
		} else {
			this.nextpage = this.currentpage + 1;
		}
		return nextpage;
	}

	//页码条
	public int[] getPagebar() {
		int startpage;
		int endpage;
		int pagebar[] = null;
		if (this.totalpage <= 10) {
			pagebar = new int[this.totalpage];
			startpage = 1;
			endpage = this.totalpage;
		} else {
			pagebar = new int[10];
			startpage = this.currentpage - 4;
			endpage = this.currentpage + 5;

			// 总页数=30 3 -1
			// 总页数=30 29 34 21 30
			if (startpage < 1) {
				startpage = 1;
				endpage = 10;
			}

			if (endpage > this.totalpage) {
				endpage = this.totalpage;
				startpage = this.totalpage - 9;
			}
		}

		int index = 0;
		for (int i = startpage; i <= endpage; i++) {
			pagebar[index++] = i;
		}

		this.pagebar = pagebar;
		return this.pagebar;

	}

}
