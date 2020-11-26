package com.etsoft.comm.generate;

import com.etsoft.comm.tool.FileHelper;
import com.etsoft.comm.tool.GContext;

public class Render1_Dao {

	public static void create(GContext ctx) {
		// ResultSet l_rset = ctx.getL_rset();
		String fileStr = ctx.getDaoSampleFileStr();

		// ★ 开始拼字符串
		fileStr = fileStr.replaceFirst("##ClassNameSuffix#", ctx.getDaoClassNameSuffix());
		fileStr = fileStr.replace("##ClassEntityName#", ctx.getEntityClassNameSuffix());

		// 输出内容
		FileHelper.writeFile(ctx.getDaoFileFullPath(), ctx.getTable_name(), fileStr);
	}

}
