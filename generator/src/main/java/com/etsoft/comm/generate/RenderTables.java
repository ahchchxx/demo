package com.etsoft.comm.generate;

import com.etsoft.comm.db.DBHelper;
import com.etsoft.comm.tool.ConsoleHelper;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RenderTables {

	public static void showAllTables() throws Exception{
		List<String> list = getAllTables();
		for(String s : list) {
			ConsoleHelper.println(s);
		}
	}
	public static List<String> getAllTables() throws Exception{
		List<String> list = new ArrayList<>();

		Connection conn = DBHelper.getConnection();
		DatabaseMetaData metaData = conn.getMetaData();
		
		ResultSet rs = metaData.getTables(conn.getCatalog(), "", null, new String[]{"TABLE"});
		while(rs.next()) {
			list.add(rs.getString("TABLE_NAME"));
		}
		
		return list;
	}
}
