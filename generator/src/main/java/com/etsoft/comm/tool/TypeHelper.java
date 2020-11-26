package com.etsoft.comm.tool;

import java.sql.ResultSetMetaData;

public class TypeHelper {
	
	public static String getTypeFromDb2Java(String typeStr){
		//ConsoleHelper.println(typeStr);
		String type = "";
		switch(typeStr){
			case "java.lang.String":
				type = "String"; break;
			case "java.sql.Timestamp":
			case "java.sql.Date":
				type = "Date"; break;
			case "java.lang.Integer":
				type = "int"; break;
			case "java.lang.Long":
			case "java.math.BigDecimal":
				type = "long"; break;
//			case "":
//				type = ""; break;
		
			default:
				type = "double"; break;
		}
		
		return type;
	}

	// convert type form db 2 java
	public static String getTypeFromDb2Java(ResultSetMetaData metaData, int i) throws Exception {
		//ConsoleHelper.println(metaData.getColumnClassName(i));
		String ret = "";
		if (metaData.getColumnClassName(i).equals("java.sql.Timestamp")) {
			ret = "Date";
		} else if (metaData.getColumnClassName(i).equals("java.lang.String")) {
			ret = "String";
		} else if (metaData.getColumnClassName(i).equals("java.lang.Short")) {
			ret = "Integer";// "int";
		} else if (metaData.getScale(i) > 0) {
			ret = "Double";// "double";
		} else { // java.math.BigInteger...
			// ret = "long";
			ret = "Long";
		}
		return ret;
	}

	// 这个针对 Access 数据库使用
	// public static String getTypeFromDb2Java(ResultSetMetaData metaData, int i) throws Exception {
	// 	//ConsoleHelper.println(metaData.getColumnClassName(i));
	// 	String ret = "";
	// 	if (metaData.getColumnClassName(i).equals("java.sql.Timestamp")) {
	// 		ret = "Date";
	// 	} else if (metaData.getColumnClassName(i).equals("java.lang.String")) {
	// 		ret = "String";
	// 	} else if (metaData.getColumnClassName(i).equals("java.lang.Short")) {
	// 		ret = "int";
	// 	} else if (metaData.getScale(i) > 0) {
	// 		ret = "double";
	// 	} else { // java.math.BigInteger...
	// 		ret = "long";
	// 	}
	// 	return ret;
	// }
}
