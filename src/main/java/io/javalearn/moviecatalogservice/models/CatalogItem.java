package io.javalearn.moviecatalogservice.models;

public class CatalogItem {
	private String name;
	private String desc;
	private Integer rating;
	public CatalogItem(String name, String desc, Integer rating) {
		super();
		this.name = name;
		this.desc = desc;
		this.rating = rating;
	}
	public String getName() {
		return name;
	}
	public String getDesc() {
		return desc;
	}
	public Integer getRating() {
		return rating;
	}	
}
