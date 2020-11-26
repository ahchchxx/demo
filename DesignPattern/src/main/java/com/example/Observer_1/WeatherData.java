package com.example.Observer_1;

public class WeatherData extends BaseObserver {

	public int fieldA, fieldB;
	
	private void paramChange() {
		notifies();
	}
	
	public void setParams(int fieldA) {
		this.fieldA = fieldA;
		paramChange();
	}
}
