package demo.src;

import org.apache.http.client.HttpResponseException;
import org.json.simple.JSONArray;

import demo.res.api.*;

public class DemoRest {

	public static void main(String[] args) {
		
		Request Request = new Request("posts", "get"){
			@Override
			public void onSuccess(JSONArray result) {
				// TODO Auto-generated method stub
				System.out.println(result.get(1).toString());
			}
			
			@Override
			public void onFailed(HttpResponseException e) {
				e.printStackTrace();
			}
			
			@Override
			public void onCancelled(Exception e) {
				e.printStackTrace();
			}
		};
	}
}
