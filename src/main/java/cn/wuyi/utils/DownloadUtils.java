package cn.wuyi.utils;

import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Encoder;

/**
 * 下载的工具类（类似火狐的浏览器的编码）
 * @author Administrator
 *
 */
public class DownloadUtils {
	
	/**
	 * 编码成base64
	 * @param filename
	 * @return
	 */
	public static String base64EncodeFileName(String filename) {
		BASE64Encoder base64Encoder = new BASE64Encoder();
		try {
			return "=?UTF-8?B?"
					+ new String(base64Encoder.encode(filename
							.getBytes("UTF-8"))) + "?=";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
