package api.test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.userEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;



// @Listeners(api.utilities.ExtentReportManager.class)
public class DataDrivenTest {
	
	// if data provider is present in same package we no need to mention data provider class name

	@Test(priority=1 , dataProvider="Data" , dataProviderClass=DataProviders.class )
	public void  testPostUser(String userID , String  userName , String fName , String lName , String userEmail , String pwd , String phoneNum)
	{
		
		
		User userPayload = new User();
		
		
		userPayload.setId(Integer.parseInt(userID.trim()));
		userPayload.setUsername(userName);
		 userPayload.setFirstName(fName);
		 userPayload.setLastName(lName);
		 userPayload.setEmail(userEmail);
		 userPayload.setPassword(pwd);
		 userPayload.setPhone(phoneNum);
		
		
		 

			Response response = userEndPoints.createUser(userPayload);
			
			response.then().log().all();
			AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
	}
	
	
	
	@Test(priority=2 ,  dependsOnMethods="testPostUser" ,dataProvider="UserNames" , dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String userName)
	{
		
		
		if(userName == null || userName.trim().isEmpty()) {
		    return;
		}
		
		
		Response response =	userEndPoints.deleteUser(userName);
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
	}
	
	
	
	
}
