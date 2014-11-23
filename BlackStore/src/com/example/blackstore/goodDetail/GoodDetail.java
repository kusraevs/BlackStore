package com.example.blackstore.goodDetail;


public class GoodDetail implements GoodDetailInterface{

	private String[] imagesUrl = {"http://img1.wildberries.ru/big/new/1720000/1726219-1.jpg",
			"http://img1.wildberries.ru/big/new/1720000/1726219-2.jpg",
			"http://img1.wildberries.ru/big/new/1720000/1726219-3.jpg",
			"http://img1.wildberries.ru/big/new/1720000/1726219-4.jpg"};
	@Override
	public String[] getImagesUrl() {
		return imagesUrl;
	}

	@Override
	public String getDescription() {
		return "Стильные кроссовки на прочной рельефной подошве. Модель эффектной расцветки. Кроссовки оформлены шнуровкой.";
	}

	@Override
	public String getName() {
		return "Кроссовки Boxfit.3";
	}
	
	@Override
	public String getBrand() {
		return "Adidas";
	}
	@Override
	public String getComp() {
		return "Текстиль 49%, синтетический материал 51%";
	}


	@Override
	public int getPrice() {
		return 4990;
	}

	@Override
	public String getArticul() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return null;
	}
}
