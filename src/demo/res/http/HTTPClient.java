package demo.res.http;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;


public class HTTPClient {
	private CloseableHttpAsyncClient httpclient;
	
	public HTTPClient() {
		httpclient = HttpAsyncClients.createDefault();
		httpclient.start();
	}
	
	public CloseableHttpAsyncClient getHttpClient(){
		return this.httpclient;
	}
	
	public HTTPRequest request(){
		return new HTTPRequest(this.httpclient); 
	}
}
