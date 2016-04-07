package demo.res.http;

import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;

//Clase request, recibe el http client y hace la ejecución según el método seleccionado
public class HTTPRequest {
	private HttpRequestBase request;
	CloseableHttpAsyncClient httpclient;
	
	public HTTPRequest(CloseableHttpAsyncClient httpclient) {
		this.httpclient = httpclient;
	}

	public HttpRequestBase httpGet(String uri){
		this.request = new HttpGet(uri);
		return this.request;
	}
	
	public HttpRequestBase httpPost(String uri){
		request = new HttpPost(uri);
		return this.request;
	}
	
	public void execute(){
		Future<HttpResponse> future = this.httpclient.execute(this.request, new FutureCallback<HttpResponse>() {
			@Override
			public void completed(final HttpResponse response) {
				ResponseHandler handler = new BasicResponseHandler();
				
				try {
					String message = handler.handleResponse(response).toString();
					HTTPRequest.this.onSuccess(message); 
				} catch (Exception e) {
					HTTPRequest.this.onFailed((HttpResponseException) e);
				}
			}
			
			@Override
			public void failed(Exception e) {
				HTTPRequest.this.onFailed((HttpResponseException) e);
			}
			
			@Override
			public void cancelled() {
				HTTPRequest.this.onCancelled();
			}
		});
		
		
	}
	public void onSuccess(String result){}
	public void onFailed(HttpResponseException e) {}
	public void onCancelled() {}

}
