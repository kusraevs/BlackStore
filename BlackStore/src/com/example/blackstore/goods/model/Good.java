package com.example.blackstore.goods.model;

public class Good {
	private String name;
	private int price;
	private String imageUrl;
	public Good(String name, int price, String imageUrl){
		this.name = name;
		this.price = price;
		this.imageUrl = imageUrl;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
