package com.example.Example_02.base;

import java.util.Timer;
import java.util.TimerTask;

public abstract class BaseController {
	protected IService service;
	
	protected abstract boolean doSomething();// 交给 具体的 controller 去做事情
	
	public boolean start() {
		// 建立 连接
		if (service.connect() == false) {
			return false;
		}
		
		return doSomething();
	}
	protected boolean send() {
		Timer time = new Timer();
		time.schedule(new TimerTask() {// 发消息
			@Override
			public void run() {
				while(true) {
					service.send();
				}
			}
		}, 0, 1000);
		
		return true;
	}
	protected boolean receive() {
		Timer time = new Timer();
		time.schedule(new TimerTask() {// 收消息
			@Override
			public void run() {
				while(true) {
					service.receive();
				}
			}
		}, 0, 1000);
		return true;
	}
	protected boolean parse() {
		Timer time = new Timer();
		time.schedule(new TimerTask() {// 解析消息
			@Override
			public void run() {
				while(true) {
					service.parse();
				}
			}
		}, 0, 1000);
		return true;
	}
	
	
	public void stop() {
		System.out.println("this controller is stopped");
	}
}
