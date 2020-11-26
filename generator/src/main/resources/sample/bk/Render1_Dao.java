package com.etsoft.comm.generate;

import com.etsoft.comm.db.DBHelper;
import com.etsoft.comm.tool.FileHelper;
import com.etsoft.comm.tool.StringUnit;

import java.sql.Connection;
import java.sql.ResultSet;

public class Render1_Dao {

//	public static String sampleFilePath = System.getProperty("user.dir") + "/sample/SampleDao.java";
	public static String sampleFilePath = System.getProperty("java.class.path").split(";")[0]
			+ "/sample/SampleDao.java";
	
	public static void create(String tableName, String entityFileSuffixStr, String tablePrefixName, String filePath, String suffixStr)
			throws Exception {
		FileHelper.checkDirectoryExsit(filePath);
		
		Connection l_conn = DBHelper.getConnection();

		// 表名
		String table_name = tableName;//.toLowerCase();// "hr_org";
		// 类名，驼峰命名法，首字母大写
		String ClassName = StringUnit.getCamelName(tableName, tablePrefixName, true);
		String ClassEntityName = ClassName + entityFileSuffixStr;
		String ClassNameSuffix = ClassName + suffixStr;
		String fileName = filePath + ClassNameSuffix + ".java";
		
		ResultSet l_rset = l_conn.prepareStatement("select * from " + table_name + " limit 1").executeQuery();
		
		// 主键
//		ResultSet l_column_key = l_conn.getMetaData().getPrimaryKeys(null, null, table_name.toUpperCase());
//		String pkField = "";// 默认主键只有一个
//		while (l_column_key.next()) {
//			for (int i = 1; i <= l_column_key.getMetaData().getColumnCount(); i++) {
//				pkField = StringUnit.isNullOrEmpty(l_column_key.getString("column_name"))
//						? "" : l_column_key.getString("column_name").toLowerCase();
//			}
//		}
//		if(StringUnit.isNullOrEmpty(pkField)) {
//			throw new Exception(table_name + " 表没有主键");
//		}

		String fileStr = FileHelper.readToString(sampleFilePath);
		
		// 开始替换
		fileStr = fileStr.replaceFirst("##ClassNameSuffix#", ClassNameSuffix);
		//fileStr = fileStr.replace("##tableName#", table_name);
		fileStr = fileStr.replace("##ClassEntityName#", ClassEntityName);
		//fileStr = fileStr.replace("##pkFieldCamel#", StringUnit.getCamelName(pkField, false));
		//fileStr = fileStr.replace("##pkField#", pkField);// 短占位符放在后面替换

		// 变量定义，不设默认值
//		String l_line = "", l_lineParams = "", l_lineParamsInsert = "", l_lineParamsUpdate = "";
//		for (int i = 1; i <= l_rset.getMetaData().getColumnCount(); i++) {
//			String col_name = l_rset.getMetaData().getColumnName(i).toLowerCase();
//			String colName = StringUnit.getCamelName(col_name, false);
//			if(col_name.equals(pkField)) {// 跳过 主键字段
//				continue;
//			}
//
//			l_lineParams += "\t\tparams.add(bean." + colName + ");\r\n";
//			l_lineParamsInsert += "?,";
//			l_lineParamsUpdate += col_name + "=?,";
//			l_line += col_name + ",";
//		}
//		fileStr = fileStr.replace("\t\t##beanFiledsParams#", l_lineParams.substring(0, l_lineParams.length() - 2));
//		fileStr = fileStr.replace("##beanFieldsInsert#", l_lineParamsInsert.substring(0, l_lineParamsInsert.length() - 1));
//		fileStr = fileStr.replace("##beanFieldsUpdate#", l_lineParamsUpdate.substring(0, l_lineParamsUpdate.length() - 1));
//		fileStr = fileStr.replace("##beanFields#", l_line.substring(0, l_line.length() - 1));// 这个放在上面两个的后面
	    
		// 输出内容
		FileHelper.writeFile(fileName, tableName, fileStr);
	}

}
