package com.example.Observer_2;

import java.util.Observable;
import java.util.Observer;

public class DisplayB implements Observer {

	public DisplayB(Observable observable) {
		observable.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("DisplaB changed");
	}

}
