package com.etsoft.comm.generate;

import com.etsoft.comm.tool.FileHelper;
import com.etsoft.comm.tool.GContext;

public class Render2_ServiceImpl {

	public static void create(GContext gCtx) throws Exception {
		String fileStr = gCtx.getServiceImplSampleFileStr();

		// ★ 开始拼字符串
		fileStr = fileStr.replace("##ClassDaoName#", gCtx.getDaoClassNameSuffix());
		fileStr = fileStr.replace("##ClassEntityName#", gCtx.getEntityClassNameSuffix());
		fileStr = fileStr.replace("##ClassServiceName#", gCtx.getServiceClassNameSuffix());
		fileStr = fileStr.replace("##ClassServiceImplName#", gCtx.getServiceImplClassNameSuffix());
		// fileStr = fileStr.replace("##pkFieldCamel#", StringUnit.getCamelName(gCtx.getPK(), false));

		// 输出内容
		FileHelper.writeFile(gCtx.getServiceImplFileFullPath(), gCtx.getTable_name(), fileStr);
	}

}
