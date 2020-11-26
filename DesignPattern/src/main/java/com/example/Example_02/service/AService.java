package com.example.Example_02.service;

import com.example.Example_02.base.BaseService;

public class AService extends BaseService {

	@Override
	public boolean send() {
		System.out.println("send msgs");
		return false;
	}

	@Override
	public boolean receive() {
		System.out.println("receive msgs");
		return false;
	}

	@Override
	public boolean parse() {
		System.out.println("parse msgs");
		return false;
	}
}
