package com.forex.restapi;

import org.testng.annotations.BeforeClass;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.testng.annotations.Test;

import com.forex.constants.Path;
import com.pzagoris.utilities.RestUtilities;
import static io.restassured.RestAssured.given;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


/*
 * MarketStatusRequests: class that contains all the requests for the MarketStatus basic path
 * 
 */
public class MarketStatusRequests {

	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	
	public MarketStatusRequests() {}
	
	public void verifyMarketStatusIsOpen() {
		
		reqSpec = RestUtilities.getRequestSpecification();
		reqSpec.basePath(Path.MARKET_STATUS);		
		resSpec  = RestUtilities.getResponsepecification();

		Response response =  given().spec(reqSpec).when().get().then().spec(resSpec).extract().response();
		
		//System.out.println(response.asString());
		JsonPath xx  = RestUtilities.getJsonPath(response);
		boolean res_x= xx.get("market_is_open");  			//check if the market is open
		assertThat(res_x,equalTo(true));
		
	}
	
			
}
