package api.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userEndPoints;
import api.endpoints.userEndPoints2;
import api.payloads.User;
import io.restassured.response.Response;



// Class is duplicated to test the Property file data driven testing 
public class UserTest2 {

	Faker faker;
	User userpayload;
	
	
	public Logger logger;
	
	@BeforeClass
public	void setUpData() {
		
		 faker = new Faker();
		 
		 userpayload= new User();
		 

		 userpayload.setId(faker.number().hashCode());
		 userpayload.setUsername(faker.name().username());
		 userpayload.setFirstName(faker.name().firstName());
		 userpayload.setLastName(faker.name().lastName());
		 userpayload.setEmail(faker.internet().emailAddress());
		 userpayload.setPassword(faker.internet().password(5,10));
		 userpayload.setPhone(faker.phoneNumber().cellPhone());
		
		 
		 // logs
		 
		logger= LogManager.getLogger(this.getClass());

	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		
		
		logger.info("**** Creating user ****");
		Response response = userEndPoints2.createUser(userpayload);
		
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**** User is created ****");

	}
	
	
	@Test(priority=2)
	public void testGetUser()
	{
		
		logger.info("**** Reading user info ****");

		
		Response response = userEndPoints2.getUser(this.userpayload.getUsername());
		
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		
		logger.info("**** User info displayed ****");

	}
	
	
	@Test(priority=3)
	public void testPutUser()
	{
		// update user details 
		
		logger.info("**** Updating user info ****");

		 userpayload.setFirstName(faker.name().firstName());
		 userpayload.setLastName(faker.name().lastName());
		 userpayload.setEmail(faker.internet().emailAddress());
	
		Response response = userEndPoints2.updateUser(this.userpayload.getUsername() , userpayload);
		response.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		// user details after update
		
		Response responseAfterUpdate = userEndPoints2.getUser(this.userpayload.getUsername());
		response.then().log().all();
		AssertJUnit.assertEquals(responseAfterUpdate.getStatusCode(), 200);

		logger.info("****  User info  is updated ****");

	}
	
	
	@Test(priority=4)
	public void testDeleteUser()
	{
		logger.info("**** Deleting user  ****");

		
		Response response = userEndPoints2.deleteUser(this.userpayload.getUsername());
		
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**** User Deleted  ****");
		
	}
	
	
	
	
	
}
