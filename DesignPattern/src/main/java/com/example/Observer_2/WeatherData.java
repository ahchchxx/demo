package com.example.Observer_2;

import java.util.Observable;

public class WeatherData extends Observable {

	public int fieldA, fieldB;
	
	private void paramChange() {
		//notifies();
		setChanged();
		notifyObservers();
	}
	
	public void setParams(int fieldA) {
		this.fieldA = fieldA;
		paramChange();
	}
}
