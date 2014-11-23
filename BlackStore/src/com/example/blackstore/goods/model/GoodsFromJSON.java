package com.example.blackstore.goods.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

public class GoodsFromJSON implements GoodsInterface{
	ArrayList<Good> goods = new ArrayList<Good>();
	public GoodsFromJSON(){
	}
	public GoodsFromJSON(String goodsJSONArrayStr){
		this.appendGoodsFromJSON(goodsJSONArrayStr);
	}
	public GoodsFromJSON appendGoodsFromJSON(String goodsJSONArrayStr){
		try {
			JSONArray goodsJSONArray = new JSONArray(goodsJSONArrayStr);
			for (int i = 0;i < goodsJSONArray.length();i++){
				String name = goodsJSONArray.getJSONObject(i).getString("name");
				//String id = goodsJSONArray.getJSONObject(i).getString("categoryId");
				goods.add(new Good(name, 100, "https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/512/sale.png"));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}
	@Override
	public Good getGood(int i) {
		return goods.get(i);
	}
	@Override
	public int size() {
		return goods.size();
	}
}
