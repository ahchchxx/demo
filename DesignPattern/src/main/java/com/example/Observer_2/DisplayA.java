package com.example.Observer_2;

import java.util.Observable;
import java.util.Observer;

public class DisplayA implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("DisplayA changed");
	}

}
