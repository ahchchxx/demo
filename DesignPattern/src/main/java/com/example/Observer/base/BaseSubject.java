package com.example.Observer.base;

import java.util.ArrayList;

import com.example.Observer.interfaces.IObserver;

public class BaseSubject {
	ArrayList<IObserver> list = new ArrayList<IObserver>();
	
	public void register(IObserver observer) {
		list.add(observer);
	}
	public void remove(IObserver observer) {
		int i = list.indexOf(observer);
		if (i >= 0) {
			list.remove(i);
		}
	}
	protected void notifies() {
		for (IObserver o : list) {
			o.udpate();
		}
	}
}
