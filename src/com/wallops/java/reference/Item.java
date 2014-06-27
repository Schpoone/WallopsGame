package com.wallops.java.reference;

import java.awt.Image;

public class Item {
	
	private String name;
	private Image image;
	private String description;
	
	public Item() {
		name = null;
		image = null;
		description = null;
	}
	
	public Item(String n, Image i, String d) {
		name = n;
		image = i;
		description = d;
	}
	
	public String getName() {
		return name;
	}
	
	public Image getImage() {
		return image;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public void setImage(Image i) {
		image = i;
	}
	
	public void setDescription(String d) {
		description = d;
	}
}
