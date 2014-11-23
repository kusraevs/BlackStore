package com.example.blackstore.goods.loader;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;

import com.example.blackstore.MyHttpClient;
public class GoodsLoader extends AsyncTaskLoader<String> {

	public final static String ARGS_SKIP = "arg_skip";
	public final static String ARGS_LIMIT = "arg_limit";
	private MyHttpClient mHttpClient;
	private int skip, limit;
	public GoodsLoader(Context context, Bundle args) {
		super(context);
		mHttpClient = new MyHttpClient();
		mHttpClient.setEncodingAndTimeout();
		skip = args.getInt(ARGS_SKIP);
		limit = args.getInt(ARGS_LIMIT);
	}

	@Override
	public String loadInBackground() {
		HttpResponse response = null;
		HttpGet httpGet = null;
		try {
			httpGet = new HttpGet(MyHttpClient.GET_ITEMS_URL + "?skip=" + skip + "&limit=" + limit);
			response = mHttpClient.execute(httpGet);
			String resItemsJSON = EntityUtils.toString(response.getEntity(), "UTF-8");
			int resCode = response.getStatusLine().getStatusCode();
			response.getEntity().consumeContent();
			return resItemsJSON;
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}