package com.pixzoo.back.model;

public class AnimalEntry {
    private String key;
    private String[] value;
    
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String[] getValue() {
		return value;
	}
	public void setValue(String[] value) {
		this.value = value;
	}
	public void setAnimalValues(String[] values) {
        this.value = values;
    }
}
