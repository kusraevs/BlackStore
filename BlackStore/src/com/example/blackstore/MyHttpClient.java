package com.example.blackstore;

import org.apache.http.HttpVersion;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public class MyHttpClient extends DefaultHttpClient {
	public static final int TIMEOUT_CONN = 5000;
	public static final int TIMEOUT_SOCKET = 6000;
	
/*
	public static final String DOMAIN = "92.63.109.251";
	public static final String IP = "http://92.63.109.251:8080";*/
	
	//public static final String DOMAIN = "62.109.21.97";
	public static final String IP = "http://blackstore-4men.rhcloud.com";

	public static final String GET_ITEMS_URL = IP + "/api/content/item";
	
	public MyHttpClient(){

	}
	public MyHttpClient(ClientConnectionManager mgr, HttpParams params){
	    super(new ThreadSafeClientConnManager(params,
	    		mgr.getSchemeRegistry()), params);
	}

	public void setEncodingAndTimeout(){
		HttpParams params = getParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, "utf-8");
		params.setBooleanParameter("http.protocol.expect-continue", false);
	
		HttpConnectionParams.setConnectionTimeout(params, TIMEOUT_CONN);
		HttpConnectionParams.setSoTimeout(params, TIMEOUT_SOCKET);
		
		this.setParams(params);
	}
	

}
