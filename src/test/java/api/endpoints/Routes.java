package api.endpoints;

public class Routes {
	
	//https://petstore.swagger.io/v2/user
//	https://petstore.swagger.io/v2/user/{username}
public static String base_url="https://petstore.swagger.io/v2";
//user module
public static String post_url=base_url+"/user";
public static String get_url=base_url+"/user/{username}";
public static String put_url=base_url+"/user/{username}";
public static String delete_url=base_url+"/user/{username}";
	
}
