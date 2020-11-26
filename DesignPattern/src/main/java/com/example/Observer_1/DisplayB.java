package com.example.Observer_1;

public class DisplayB implements IObserver {

	@Override
	public void udpate() {
		System.out.println("DisplayB changed");
	}

}
