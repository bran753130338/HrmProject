package cn.wuyi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class WebUtils {
	public static <T> T request2Bean(HttpServletRequest request, Class<T> beanClass) {
		try {
			T bean = beanClass.newInstance();
			// 得到request里面所有数据
			Map map = request.getParameterMap();

			/**
			 * 处理注册转换器：
			 * 
			 * 注意： Converter只支持一些基本的类型，Date类型不支持，当遇到不认识的类型时，会抛出异常，
			 * 
			 * 对于Date类型，实现了一个Converter,添加了一个设置日期格式的函数。
			 * 
			 * ConvertUtils.register(new DateConvert(), java.utils.Date.class);
			 * 
			 * 是在web项目中，如果没有注册日期的话，formbean中将不支持java.utils.Date类型，并抛出异常
			 */
			ConvertUtils.register(new Converter() {

				public Object convert(Class type, Object value) {
					if (value == null) {
						return null;
					}
					String str = (String) value;
					if (str.trim().equals("")) {
						return null;
					}

					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					try {

						/**
						 * parse()返回的是一个Date类型数据，format返回的是一个String类型的数据
						 * 
						 *parse()把String型的字符串转换成特定格式的date类型  
						 */

						return df.parse(str);
					} catch (ParseException e) {
						throw new RuntimeException(e);
					}
				}
			}, Date.class);
			/**
			 * 
			 * BeanUtils.populate（）方法：
			 * 用来将一些 key-value 的值（例如 hashmap）映射到 bean 中的属性。
			 */
			BeanUtils.populate(bean, map);
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
