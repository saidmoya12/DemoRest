package demo.res.api;

import org.apache.http.client.HttpResponseException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import demo.res.http.HTTPRequest;

public class Request{
	
	public Request(String path, String method) {
		ManageAPI.init();
		HTTPRequest request = new HTTPRequest(ManageAPI.client){
			public void onSuccess(String result) {
				JSONParser parser = new JSONParser();
				JSONArray res;
				try {
					res = (JSONArray) parser.parse(result);
					Request.this.onSuccess(res);
				} catch (ParseException e) {
					res = new JSONArray();
				}
			}
			public void onFailed(HttpResponseException e){
				e.printStackTrace();
			}
			
			@Override
			public void onCancelled() {
				System.out.println("canceled");
			}
		};
		if(new String(method).equals("get")){
			request.httpGet(APIEndpoints.generate(path))
				//.setHeader(new BasicHeader("auth", ManageAPI.userAuth))
			;
			
		}
		
		request.execute();
	}
	
	public void onSuccess(JSONArray result){}
	public void onFailed(HttpResponseException e) {}
	public void onCancelled(Exception e) {}
	
}
