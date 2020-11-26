package com.etsoft.comm.generate;

import com.etsoft.comm.db.DBHelper;
import com.etsoft.comm.tool.FileHelper;
import com.etsoft.comm.tool.StringUnit;

import java.sql.Connection;
import java.sql.ResultSet;

public class Render3_Controller {

	// public static String sampleFilePath = System.getProperty("user.dir")
	public static String sampleFilePath = System.getProperty("java.class.path").split(";")[0]
			+ "/sample/SampleController.java";

	public static void create(String tableName, String filePath, String suffixStr) throws Exception {
		FileHelper.checkDirectoryExsit(filePath);

		Connection l_conn = DBHelper.getConnection();

		// 表名
		String table_name = tableName;//.toLowerCase();// "hr_org";
		// 类名，驼峰命名法，首字母大写
		String ClassName = StringUnit.getCamelName(tableName, true);
		String ClassEntityName = ClassName + "Info";
		String ClassServiceName = ClassName + "Service";
		String ClassServletName = ClassName + "Servlet";
		String ClassNameSuffix = ClassName + suffixStr;
		String fileName = filePath + ClassNameSuffix + ".java";

		// 主键
		ResultSet l_column_key = l_conn.getMetaData().getPrimaryKeys(null, null, table_name.toUpperCase());
		String pkField = "";// 默认主键只有一个
		while (l_column_key.next()) {
			for (int i = 1; i <= l_column_key.getMetaData().getColumnCount(); i++) {
				pkField = StringUnit.isNullOrEmpty(l_column_key.getString("column_name"))
						? "" : l_column_key.getString("column_name").toLowerCase();
			}
		}
		if(StringUnit.isNullOrEmpty(pkField)) {
			throw new Exception(table_name + " 表没有主键");
		}

		String fileStr = FileHelper.readToString(sampleFilePath);
		
		// 开始替换
		fileStr = fileStr.replaceFirst("##ClassNameSuffix#", ClassNameSuffix);
		fileStr = fileStr.replace("##ClassEntityName#", ClassEntityName);
		fileStr = fileStr.replace("##ClassServiceName#", ClassServiceName);
		fileStr = fileStr.replace("##ClassServletName#", ClassServletName);
		fileStr = fileStr.replace("##pkFieldCamel#", StringUnit.getCamelName(pkField, false));

		// 输出内容
		FileHelper.writeFile(fileName, tableName, fileStr);
	}

}
