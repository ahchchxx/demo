package com.example.Observer_1;

public class DisplayA implements IObserver {

	@Override
	public void udpate() {
		System.out.println("DisplayA changed");
	}

}
