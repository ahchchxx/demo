package com.etsoft.comm.generate;

import com.etsoft.comm.tool.*;
import java.sql.ResultSet;

public class Render0_Entity {

	public static void create(GContext ctx) throws Exception {
		ResultSet l_rset = ctx.getL_rset();
		boolean l_date = ctx.is_date_exsit();
		// 读取 sample 模板
		String fileStr = ctx.getEntitySampleFileStr();
		
		// ★ 开始拼字符串
		// 是否导入日期包
		if (l_date) {
			fileStr = fileStr.replaceFirst("##importDate#", "import java.util.Date;");
		} else {
			fileStr = fileStr.replaceFirst("##importDate#\r\n", "");
		}

		// 换 表名
		fileStr = fileStr.replaceFirst("##table_name#", ctx.getTable_name());
		// 换 类名
		fileStr = fileStr.replace("##ClassNameSuffix#", ctx.getEntityClassNameSuffix());

		// 变量定义，不设默认值
		MyStringBuilder sb = new MyStringBuilder();
		for (int i = 1; i <= l_rset.getMetaData().getColumnCount(); i++) {
			String col_name = l_rset.getMetaData().getColumnName(i).toLowerCase();
			if (GContext.defaultList.indexOf(col_name) >= 0) {
				continue; // 这些封装在父类里了
			}
			String colName = StringUnit.getCamelName(col_name, false);
			String ColName = StringUnit.getCamelName(col_name, true);

			sb.appendLine("\t@ApiModelProperty(value = \""+ ColName +"\")");
			sb.append("\tprivate ");
			sb.append(TypeHelper.getTypeFromDb2Java(l_rset.getMetaData(), i));
			sb.append(" " + colName + ";\n");
		}
		fileStr = fileStr.replaceFirst("\t##Fields#", sb.toString());

		// 输出内容
		FileHelper.writeFile(ctx.getEntityFileFullPath(), ctx.getTable_name(), fileStr);
	}
}
