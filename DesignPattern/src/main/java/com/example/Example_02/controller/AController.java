package com.example.Example_02.controller;

import com.example.Example_02.base.BaseController;
import com.example.Example_02.service.AService;

public class AController extends BaseController {

	public AController() {
		service = new AService();
	}

	@Override
	protected boolean doSomething() {
		send();
//		receive();
//		parse();
		
		return true;
	}

}
