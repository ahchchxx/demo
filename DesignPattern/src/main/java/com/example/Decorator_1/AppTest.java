package com.example.Decorator_1;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class AppTest {

	public static void main(String[] args) throws Exception {

		int c;
		FileInputStream fileInputStream = new FileInputStream("test.txt");// 默认在当前工程的目录  System.getProperty("user.dir")+
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
		InputStream inputStream = new LowerCaseInputStream(bufferedInputStream);
		while ((c = inputStream.read()) > 0) {
			System.out.print((char)c);
		}
		inputStream.close();
		
	}

}
