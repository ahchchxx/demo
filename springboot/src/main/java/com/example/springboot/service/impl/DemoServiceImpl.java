package com.example.springboot.service.impl;

import com.example.springboot.annotaton.GPService;
import com.example.springboot.service.IDemoService;

@GPService
public class DemoServiceImpl implements IDemoService{

	public String get(String name) {
		return "my name is " + name;
	}
}
