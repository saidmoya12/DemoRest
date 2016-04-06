package demo.res.api;

final class APIEndpoints{
	private static String APIRoot = "http://jsonplaceholder.typicode.com";
	
	public static String generate(String path){
		return APIRoot+"/"+path;
	}
	
	public static String generate(String path, String param){
		String url = APIRoot+"/"+path;
		return url + "/" + param;
	}
}