package com.etsoft.comm.generate;

import com.etsoft.comm.tool.FileHelper;
import com.etsoft.comm.tool.GContext;
import com.etsoft.comm.tool.StringUnit;

public class Render2_IService {

	public static void create(GContext gCtx) throws Exception {
		String fileStr = gCtx.getServiceSampleFileStr();

		// ★ 开始拼字符串
		// fileStr = fileStr.replace("##ClassDaoName#", gCtx.getDaoClassNameSuffix());
		fileStr = fileStr.replace("##ClassEntityName#", gCtx.getEntityClassNameSuffix());
		fileStr = fileStr.replace("##ClassServiceName#", gCtx.getServiceClassNameSuffix());
		// fileStr = fileStr.replace("##pkFieldCamel#", StringUnit.getCamelName(gCtx.getPK(), false));

		// 输出内容
		FileHelper.writeFile(gCtx.getServiceFileFullPath(), gCtx.getTable_name(), fileStr);
	}

}
