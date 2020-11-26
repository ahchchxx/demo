package com.example.Observer;

import com.example.Observer.interfaces.IObserver;

public class DisplayB implements IObserver {

	@Override
	public void udpate() {
		System.out.println("DisplayB changed");
	}

}
