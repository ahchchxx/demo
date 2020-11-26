package com.example.springboot.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.springboot.annotaton.GPAutowired;
import com.example.springboot.annotaton.GPController;
import com.example.springboot.annotaton.GPRequestMapping;
import com.example.springboot.annotaton.GPRequstParam;
import com.example.springboot.service.IDemoService;

@GPController
public class MvcAction {

	@GPAutowired 
	private IDemoService demoService;
	
	@GPRequestMapping("/query.json")
	public void query(HttpServletRequest req, HttpServletResponse resp, @GPRequstParam("name") String name) {
		String result = demoService.get(name);
		try {
			resp.getWriter().println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
