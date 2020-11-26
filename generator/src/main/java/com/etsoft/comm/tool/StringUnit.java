package com.etsoft.comm.tool;

public class StringUnit {
	// t_, t_hr_org → hrOrg，HrOrg
	public static String getCamelName(String name, String removePrefix, boolean isFirstUpperCase){
		return getCamelName(name.replaceFirst(removePrefix, ""), isFirstUpperCase);
	}
	// hr_org → hrOrg，HrOrg
	public static String getCamelName(String name, boolean isFirstUpperCase){
		String[] list = name.split("_");
		String ret = "";
		boolean first = true;
		for (String n : list) {
			if (n.equals("")) {
				continue;
			}
			if (first) {// 第一个单词
				ret = isFirstUpperCase ? n.substring(0, 1).toUpperCase() + n.substring(1).toLowerCase() // 首字母大写 
						: n.toLowerCase(); // 全部字母 小写
				first = false;
			} else {
				ret += (n.substring(0, 1).toUpperCase() + n.substring(1).toLowerCase());
			}
		}
		return ret;
	}
	
	public static boolean isNullOrEmpty(String str) {
		if(null == str || "".equals(str)) {
			return true;// 空、null
		}
		return false;
	}
	
}
