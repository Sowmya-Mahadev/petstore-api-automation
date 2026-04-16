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
import api.payloads.User;
import io.restassured.response.Response;

public class UserTest {

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
		Response response = userEndPoints.createUser(userpayload); // 
		
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**** User is created ****");

	}
	
	
	@Test(priority=2)
	public void testGetUser()
	{
		
		logger.info("**** Reading user info ****");

		
		Response response = userEndPoints.getUser(this.userpayload.getUsername());
		
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
	
		Response response = userEndPoints.updateUser(this.userpayload.getUsername() , userpayload);
		response.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		// user details after update
		
		Response responseAfterUpdate = userEndPoints.getUser(this.userpayload.getUsername());
		response.then().log().all();
		AssertJUnit.assertEquals(responseAfterUpdate.getStatusCode(), 200);

		logger.info("****  User info  is updated ****");

	}
	
	
	@Test(priority=4)
	public void testDeleteUser()
	{
		logger.info("**** Deleting user  ****");

		
		Response response = userEndPoints.deleteUser(this.userpayload.getUsername());
		
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**** User Deleted  ****");
		
	}
	
	
	
	
	
}
