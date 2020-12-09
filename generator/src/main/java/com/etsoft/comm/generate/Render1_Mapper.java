package com.etsoft.comm.generate;

import com.etsoft.comm.tool.FileHelper;
import com.etsoft.comm.tool.GContext;

public class Render1_Mapper {

	public static void create(GContext ctx) {
		// ResultSet l_rset = ctx.getL_rset();
		String fileStr = ctx.getDaoXmlSampleFileStr();

		// ★ 开始拼字符串
		fileStr = fileStr.replaceFirst("##ClassDaoName#", ctx.getDaoClassNameSuffix());
		fileStr = fileStr.replace("##ClassEntityName#", ctx.getEntityClassNameSuffix());

		// 输出内容
		FileHelper.writeFile(ctx.getDaoXmlFileFullPath(), ctx.getTable_name(), fileStr);
	}

}
