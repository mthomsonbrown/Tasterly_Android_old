package com.ookamijin.tasterly;

public class Bottle {
	public int icon;
	public String name;
	public String photoPath;
	public int id;

	public Bottle() {
		super();
	}

	public Bottle(int icon, String name, int id) {
		super();
		this.icon = icon;
		this.name = name;
		this.photoPath = null;
		this.id = id;
	}

	public Bottle(String photoPath, String name, int id) {
		super();
		this.icon = 0;
		this.name = name;
		this.photoPath = photoPath;
		this.id = id;
	}
}
