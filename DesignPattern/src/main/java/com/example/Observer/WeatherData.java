package com.example.Observer;

import com.example.Observer.base.BaseSubject;

public class WeatherData extends BaseSubject {

	public int fieldA, fieldB;
	
	private void paramChange() {
		notifies();
	}
	
	public void setParams(int fieldA) {
		this.fieldA = fieldA;
		paramChange();
	}
}
