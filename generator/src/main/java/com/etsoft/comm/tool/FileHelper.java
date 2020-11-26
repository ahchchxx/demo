package com.etsoft.comm.tool;

import java.io.*;

public class FileHelper {
	
//	public static void main(String[] arg) {
//		testWriteFile("D:\\asdf.txt", "asdfasdf");
//		
//		System.out.println("ok");
//	}
	
	public static String readToString(String fileName) {
		String encoding = "UTF-8";
		File file = new File(fileName);
		if(!file.exists()) {
			ConsoleHelper.println("Sample文件不存在");
			return "";
		}
		
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(filecontent);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return new String(filecontent, encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}

//		return new String(filecontent);
	}
	
	public static void writeFile(String fileName, String tableName, String writeStr){
		try{
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			writer.println(writeStr);
			writer.flush();
			writer.close();
			
			ConsoleHelper.println("ok	" + fileName);
		} catch (Exception ex) {
			ConsoleHelper.println("error	" + tableName + "	" + fileName);
	
			PrintWriter writer;
			try {
				writer = new PrintWriter(fileName, "UTF-8");
				writer.println(tableName);
				writer.flush();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// ex.printStackTrace();
		}
	}
//	public static void testWriteFile(String fileName, String writeStr){
//		File file = new File(fileName);
//		try {
//			FileOutputStream out = new FileOutputStream(file);
//			PrintStream print = new PrintStream(out);
//			print.println(writeStr);
//			
//			print.close();
//			file.deleteOnExit();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		
//	}
	
	/**
	 * 检查路径是否存在，不存在的就递归创建
	 * @param filePath
	 */
	public static void checkDirectoryExsit(String filePath) {
		File file = new File(filePath);
		if(!file.exists() && !file.isDirectory()) {
			file.mkdirs();// 递归创建
			// file.mkdir();
		}
	}
}
