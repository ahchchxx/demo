package com.etsoft.comm.generate;

import com.etsoft.comm.db.DBHelper;
import com.etsoft.comm.tool.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;

public class Render0_Entity {

//	public static String sampleFilePath = System.getProperty("user.dir") + "/sample/SampleEntity.java";
	public static String sampleFilePath = System.getProperty("java.class.path").split(";")[0]
		+ "/sample/SampleEntity.java";

	public static void create(GContext ctx, String filePath, String suffixStr) throws Exception {
		FileHelper.checkDirectoryExsit(filePath);

		// 表名
		String table_name = ctx.getTable_name();// "hr_org";
		// 类名，驼峰命名法，首字母大写
		String ClassName = ctx.getClassName(true);
		String ClassNameSuffix = ClassName + suffixStr;
		String fileName = filePath + ClassNameSuffix + ".java";
		
		ResultSet l_rset = ctx.getL_rset();
		
		boolean l_date = ctx.is_date_exsit();

		// 读取 sample 模板
		String fileStr = FileHelper.readToString(sampleFilePath);
		
		// 拼字符串
		// 是否导入日期包
		if (l_date) {
			fileStr = fileStr.replaceFirst("##importDate#", "import java.util.Date;");
		}else {
			fileStr = fileStr.replaceFirst("##importDate#\r\n", "");
		}
		
		// 类名
		fileStr = fileStr.replace("##ClassNameSuffix#", ClassNameSuffix);

		// 变量定义，不设默认值
		MyStringBuilder sb = new MyStringBuilder();
		for (int i = 1; i <= l_rset.getMetaData().getColumnCount(); i++) {
			String col_name = l_rset.getMetaData().getColumnName(i).toLowerCase();
			if (GContext.defaultList.indexOf(col_name) >= 0) {
				continue; // 这些封装在父类里了
			}
			String colName = StringUnit.getCamelName(col_name, false);
			
			sb.appendLine("\t@ApiModelProperty(value = \""+ colName +"\")");
			sb.append("\tprivate ");
			sb.append(TypeHelper.getTypeFromDb2Java(l_rset.getMetaData(), i));
			sb.append(" " + colName + ";\n");
		}
		fileStr = fileStr.replaceFirst("\t##Fields#", sb.toString());

		// 输出内容
		FileHelper.writeFile(fileName, table_name, fileStr);
	}


	public static void createEntity0(String tableName, String filePath, String suffixStr) throws Exception {
		//Connection l_conn = aCreateClass.getConn();
		Connection l_conn = DBHelper.getConnection();

		// 表名
		String table_name = tableName.toLowerCase();// "hr_org";
		// 类名，驼峰命名法，首字母大写
		String ClassName = StringUnit.getCamelName(tableName, true);
		String ClassNameSuffix = ClassName + suffixStr;
		String fileName = filePath + ClassNameSuffix + ".java";
		
		ResultSet l_rset = l_conn.prepareStatement("select * from " + table_name).executeQuery();
		
		// 主键
		ResultSet l_column_key = l_conn.getMetaData().getPrimaryKeys(null, null, table_name.toUpperCase());
		HashMap<String, String> l_pri = new HashMap<String, String>();
		while (l_column_key.next()) {
			for (int i = 1; i <= l_column_key.getMetaData().getColumnCount(); i++) {
				l_pri.put(l_column_key.getString("column_name").toLowerCase(), "");
			}
		}
		//if (l_pri.size() < 1) {
			// sb.append("该表没有主键不可以生成！");
			// return;
		//}
		
		boolean l_date = false;// 有无时间类型
		for (int i = 1; i <= l_rset.getMetaData().getColumnCount(); i++) {
			if (l_rset.getMetaData().getColumnClassName(i).equals("java.sql.Timestamp")) {
				l_date = true;
				break;
			}
		}

		// 拼字符串
		MyStringBuilder sb = new MyStringBuilder();
		sb.appendLine("package com.etsoft.entity;");
		sb.append("\n\n");
		if (l_date) {
			sb.appendLine("import java.util.Date;");
		}
		sb.append("\n");

		// 类名
		sb.appendLine("public class " + ClassNameSuffix + " { // extends BaseBean {");
		for (int i = 1; i <= l_rset.getMetaData().getColumnCount(); i++) {
			String col_name = l_rset.getMetaData().getColumnName(i).toLowerCase();
			String colName = StringUnit.getCamelName(col_name, false);
			
			// 变量定义，不设默认值
			String l_line = "	public ";//private
			l_line += TypeHelper.getTypeFromDb2Java(l_rset.getMetaData(), i);
			l_line += " " + colName + ";";
			sb.appendLine(l_line);
			
			sb.append("\n");
		}
		sb.append("\n");
		
		// get、set
		for (int i = 1; i <= l_rset.getMetaData().getColumnCount(); i++) {
			String colName = l_rset.getMetaData().getColumnName(i).toLowerCase();
			
			String l_name = StringUnit.getCamelName(colName, true);
			String l_name1 = StringUnit.getCamelName(colName, false);
			
			String l_type = TypeHelper.getTypeFromDb2Java(l_rset.getMetaData(), i);

			sb.appendLine("	public " + l_type + " get" + l_name + "(){");
			sb.appendLine("		return " + l_name1 + ";");
			sb.appendLine("	}");
			sb.appendLine("	public void set" + l_name + "(" + l_type + " " + l_name1 + "){");
			sb.appendLine("		this." + l_name1 + " = " + l_name1 + ";");
			sb.appendLine("	}");
			
			sb.append("\n");
		}
		sb.append("\n");
		
		// toString()
		sb.appendLine("	@Override");
		sb.appendLine("	public String toString() {");
		sb.append("		return \"" + ClassNameSuffix + " [\"");
		for (int i = 1; i <= l_rset.getMetaData().getColumnCount(); i++) {
			String colName = l_rset.getMetaData().getColumnName(i).toLowerCase();
			String l_name1 = StringUnit.getCamelName(colName, false);

			if(i == 1){
				sb.append(" + \"" + l_name1 + "=\" + " + l_name1);
			}else{
				sb.append(" + \", " + l_name1 + "=\" + " + l_name1);
			}
		}
		sb.appendLine(" + \"]\";");
		sb.appendLine("	}");
	    
		sb.append("}");
		
		// 输出内容
		FileHelper.writeFile(fileName, tableName, sb.toString());
	}
}
