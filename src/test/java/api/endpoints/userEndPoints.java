package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// To perform CRUD operation
public class userEndPoints {

	public static Response createUser(User payload) {

		Response response = given()
				              .accept(ContentType.JSON)
				              .contentType(ContentType.JSON)
				              .body(payload).when()
				          .post(Routes.post_url);

		return response;
	}

	public static Response getUser(String userName) {

		Response response = given()
				.pathParam("username", userName)
				.get(Routes.get_url);
		return response;

	}
	
	public static Response updateUser(String userName , User payload){

		Response response = given()
				              .accept(ContentType.JSON)
				              .contentType(ContentType.JSON)
				              .pathParam("username", userName)
				              .body(payload).when()
				          .put(Routes.put_url);

		return response;
	}

	
	public static Response deleteUser(String userName) {

		Response response = given()
				.pathParam("username", userName)
				.delete(Routes.delete_url);
		return response;

	}

}
