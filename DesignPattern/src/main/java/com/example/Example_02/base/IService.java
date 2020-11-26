package com.example.Example_02.base;

public interface IService {

	public boolean connect();
	public abstract boolean send();
	public abstract boolean receive();
	public abstract boolean parse();
	
}
