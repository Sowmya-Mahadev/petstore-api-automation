package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// To perform CRUD operation
public class userEndPoints2 {

	
	// Method created for getting URLs from Property file
	static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes"); // loads properties file
		return routes;
	}
	
	
	
	
	
	public static Response createUser(User payload) {

		
		String post_url = getURL().getString("post_url");
		
		Response response = given()
				              .accept(ContentType.JSON)
				              .contentType(ContentType.JSON)
				              .body(payload).when()
				          .post(post_url);

		return response;
	}

	public static Response getUser(String userName) {

		String get_url = getURL().getString("get_url");

		
		
		Response response = given()
				.pathParam("username", userName)
				.get(get_url);
		return response;

	}
	
	public static Response updateUser(String userName , User payload){

		String put_url = getURL().getString("put_url");
		
		Response response = given()
				              .accept(ContentType.JSON)
				              .contentType(ContentType.JSON)
				              .pathParam("username", userName)
				              .body(payload).when()
				          .put(put_url);

		return response;
	}

	
	public static Response deleteUser(String userName) {

		String delete_url = getURL().getString("delete_url");
		
		Response response = given()
				.pathParam("username", userName)
				.delete(delete_url);
		return response;

	}

}
