package com.example.Example_02.base;

public abstract class BaseService implements IService {

	@Override
	public boolean connect() {
		System.out.println("connected");
		
		return false;
	}

}
