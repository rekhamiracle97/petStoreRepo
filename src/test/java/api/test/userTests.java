package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import io.restassured.response.Response;

public class userTests {
	Faker fake;
	User userPayload;
	public Logger logger;
	@BeforeClass
	public void setupData()
	{
		fake=new Faker();
		userPayload= new User();
		
		userPayload.setId(fake.idNumber().hashCode());
		userPayload.setUsername(fake.name().username());
		userPayload.setFirstName(fake.name().firstName());
		userPayload.setLastName(fake.name().lastName());
		userPayload.setEmail(fake.internet().safeEmailAddress());
		userPayload.setPassword(fake.internet().password(5,10));
		userPayload.setPhone(fake.phoneNumber().cellPhone());
		//logs
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testPostUser() {
		logger.info("Creating user");
		Response response=UserEndpoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("Created user");
		
		
	}
	
	@Test(priority=2)
	public void testGEtUserByUsername() {
		logger.info("Getting user");
	Response response=	UserEndpoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		logger.info("Displayed user");
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
	}
	
	@Test(priority=3)
	public void testUpdateUserByUsername() {
		logger.info("updating user");
		userPayload.setFirstName(fake.name().firstName());
		userPayload.setLastName(fake.name().lastName());
		userPayload.setEmail(fake.internet().safeEmailAddress());
	Response response=	UserEndpoints.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		//checking data after updation
		Response afterUpdateResponse=	UserEndpoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(afterUpdateResponse.getStatusCode(), 200);
		logger.info("updated user");
		
	}
	
	@Test(priority=4)
	public void testDeleteUserByUsername() {
		logger.info("deleting  user");
	Response response=	UserEndpoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		response.then().log().body().statusCode(200);
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("deleted user");
		
		
	}
	

}
