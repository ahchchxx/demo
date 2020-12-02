package com.etsoft.comm.tool;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

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

	// convert type form db 2 java, (mysql)
	public static String getTypeFromDb2Java(ResultSetMetaData metaData, int i) throws Exception {
		// ConsoleHelper.println(metaData.getColumnName(i) + "," + metaData.getColumnClassName(i));
		String ret = "";
		if (isDateType(metaData, i)) {
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

	public static boolean isDateType(ResultSetMetaData metaData, int i) throws SQLException {
		// they are not separate now.
		// @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
		// @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		if (metaData.getColumnClassName(i).equals("java.sql.Timestamp")
				|| metaData.getColumnClassName(i).equals("java.sql.Date")) {
			return true;
		}
		return false;
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
