package com.example.Observer;

import com.example.Observer.interfaces.IObserver;

public class DisplayA implements IObserver {

	@Override
	public void udpate() {
		System.out.println("DisplayA changed");
	}

}
