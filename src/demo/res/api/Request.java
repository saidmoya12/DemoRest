package demo.res.api;

import org.apache.http.client.HttpResponseException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import demo.res.http.HTTPRequest;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
				new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
			}
			
			@Override
			public void onCancelled() {
				new Alert(AlertType.INFORMATION, "Canceled").showAndWait();
			}
		};
		
		switch(method){
			case "get":
				request.httpGet(APIEndpoints.generate(path))
					//.setHeader(new BasicHeader("auth", ManageAPI.userAuth))
				;
			break;
		}
		
		request.execute();
	}
	
	public void onSuccess(JSONArray result){}
	public void onFailed(HttpResponseException e) {}
	public void onCancelled(Exception e) {}
	
}
