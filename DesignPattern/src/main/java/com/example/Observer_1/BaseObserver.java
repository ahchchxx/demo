package com.example.Observer_1;

import java.util.ArrayList;

public class BaseObserver {

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
