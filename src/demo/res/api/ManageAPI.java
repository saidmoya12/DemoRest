package demo.res.api;

import java.util.HashMap;


import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;


public class ManageAPI {
	static CloseableHttpAsyncClient client = null; 
	private static String userAuth = null; 
	
	public static void init(){
		if(ManageAPI.client == null){
			java.net.CookieHandler.setDefault(new java.net.CookieManager());
			
			ManageAPI.client = HttpAsyncClients.createDefault();
			ManageAPI.client.start();
		}	
	}
}

