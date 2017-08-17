package com.web.app.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 类描述:读取properties配置文件     
 * 类名称：PropertyUtil     
 * 创建人：梁瑞   
 * 创建时间：2014年4月22日 下午3:56:20     
 * @version 1.0
 */
public class PropertyUtil {

	public static Properties getResourceFile(String FileName) {
		
		Properties pro = new Properties();
		try {
			InputStream inputStream = PropertyUtil.class.getClassLoader().getResourceAsStream(FileName);
			BufferedReader bfPropFile = new BufferedReader(new InputStreamReader(inputStream));
			pro.load(bfPropFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pro;
	}
}
