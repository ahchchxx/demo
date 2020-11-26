package com.etsoft.comm;

import com.etsoft.comm.generate.*;
import com.etsoft.comm.tool.ConsoleHelper;
import com.etsoft.comm.tool.GContext;

import java.util.ArrayList;
import java.util.List;

public class aCreateClass {
	public static void main(String[] args) throws Exception {
		// 第一步，查找全部数据表。
		// 		可选择部分需要生成表，进行生成
//		FirstStep();

		// 第二步，选择需要生成的表
		SecondStep();
	}

	private static void FirstStep() throws Exception {
		// 1，列出来所有的表
		List<String> list = RenderTables.getAllTables();
		ConsoleHelper.print("\n");
		for(String s : list) {
			ConsoleHelper.println("\tlists.add(\"" + s + "\");");
		}
	}

	private static void SecondStep() throws Exception {
		List<String> lists = new ArrayList<>();
		// lists.add("t_crm_client");
		// lists.add("t_crm_employer");
		lists.add("t_crm_employee");

		RenderAllList(lists);
	}

	private static void RenderAllList(List<String> lists) throws Exception {
		if(lists.size() < 1) {
			return;
		}

		// 基本配置
		String codeFilePath = "D:\\spring-boot-logs\\";// "D:\\CodeRender\\";

		for(String table_name : lists) {
			GContext gContext = new GContext(table_name, codeFilePath);
			// 生成 entity
			Render0_Entity.create(gContext);

			// 生成 dao
			Render1_Dao.create(gContext);

			// 生成 service
			Render2_IService.create(gContext);
			Render2_ServiceImpl.create(gContext);

			// 生成 servlet
			Render3_Controller.create(gContext);
		}
	}

/*	private static void RenderAllList0(List<String> lists) throws Exception {
		if(lists.size() < 1) {
			return;
		}

		// 基本配置
		String codeFilePath = "D:\\spring-boot-logs\\";// "D:\\CodeRender\\";
		String tablePrefixName = "t_";

		// 生成 entity
		String entityFoldSuffixStr = "entity";
		String entityFileSuffixStr = "Info";
		String entityFilePath = codeFilePath + entityFoldSuffixStr + "\\";
		for(String tableName : lists) {
			Render0_Entity.create(tableName, tablePrefixName, entityFilePath, entityFileSuffixStr);
		}

		// 生成 dao
		String daoFoldSuffixStr = "dao";
		String daoFileSuffixStr = "Dao";
		String daoFilePath = codeFilePath + daoFoldSuffixStr + "\\";
		for(String tableName : lists) {
			Render1_Dao.create(tableName, entityFileSuffixStr, tablePrefixName, daoFilePath, daoFileSuffixStr);
		}

		// 生成 service
		String serviceFoldSuffixStr = "service";
		String serviceFileSuffixStr = "Service";
		String serviceFilePath = codeFilePath + serviceFoldSuffixStr + "\\";
		for(String tableName : lists) {
			Render2_Service.create(tableName, serviceFilePath, serviceFileSuffixStr);
		}

		// 生成 servlet
		String servletFoldSuffixStr = "controller";
		String servletFileSuffixStr = "Controller";
		String servletFilePath = codeFilePath + servletFoldSuffixStr + "\\";
		for(String tableName : lists) {
			Render3_Controller.create(tableName, servletFilePath, servletFileSuffixStr);
		}
	}*/
	
	// backup
	// 获取，包下面全部的类
//	String packageName = "com.etsoft.entity";
//	List<Class<?>> list = ClassHelper.getClasses(packageName);
//	for (Class<?> cls : list) {
//		ConsoleHelper.println(cls);
//	}
	
	// 显示 数据库中所有的表
//	RenderTable.showAllTables();
}
