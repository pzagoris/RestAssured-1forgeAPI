
/*
 * RestUtilities: class for request and response specifications
 */


package com.pzagoris.utilities;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThan;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.forex.constants.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class RestUtilities {
	
	public static String ENDPOINT;
	public static RequestSpecBuilder REQUEST_BUILDER;
	public static RequestSpecification REQUEST_SPEC;
	public static ResponseSpecBuilder RESPONSE_BUILDER;
	public static ResponseSpecification RESPONSE_SPEC;


	public static void setEndPoint(String endpoint) {
		ENDPOINT  =  endpoint;
	}

	
	public static RequestSpecification getRequestSpecification(){
		
		//read api key from the properties file
		Properties prop = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream("./ApiKey.properties");
			prop.load(fis);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(prop.getProperty("API_KEY"));
		String fullPath = Path.BASE_URI+Path.CODE_VERSION;
		REQUEST_BUILDER = new RequestSpecBuilder();
		REQUEST_BUILDER.setBaseUri(fullPath);
		REQUEST_BUILDER.addQueryParam("api_key", prop.getProperty("API_KEY"));
		REQUEST_SPEC = REQUEST_BUILDER.build();
		return REQUEST_SPEC;
	}
	
	public static ResponseSpecification getResponsepecification(){
		RESPONSE_BUILDER = new ResponseSpecBuilder();
		RESPONSE_BUILDER.expectStatusCode(200);					// response should be 200
		//RESPONSE_BUILDER.expectContentType(ContentType.JSON);
		RESPONSE_BUILDER.expectResponseTime(lessThan(3L), TimeUnit.SECONDS); //response time should be less thatn 3 seconds
		RESPONSE_SPEC = RESPONSE_BUILDER.build();
		return RESPONSE_SPEC;	
	}
	
	// some more useful methods for request and response specification in order user to expand it accordingly (if needed)
	public static RequestSpecification createQueryParam(RequestSpecification rspec, String param, String val) {
		return rspec.queryParam(param, val);
	}
	
	public static RequestSpecification createQueryParam(RequestSpecification rspec, Map<String, String> qMap) {
		return rspec.queryParams(qMap);
	}

	public static RequestSpecification createPathParam(RequestSpecification rspec, String param, String val) {
		return rspec.pathParam(param, val);
	}
	
	public static Response getResponce(){
		return given().get(ENDPOINT);
	}
	
	public static Response getResponce(RequestSpecification reqSpec, String type) {
		REQUEST_SPEC.spec(reqSpec);
		Response response = null;
		if (type.equalsIgnoreCase("get")) {
			response = given().spec(REQUEST_SPEC).get(ENDPOINT);
		}else if (type.equalsIgnoreCase("post")) {
			response = given().spec(REQUEST_SPEC).post(ENDPOINT);
		}else if (type.equalsIgnoreCase("put")) {
			response = given().spec(REQUEST_SPEC).put(ENDPOINT);
		}else if (type.equalsIgnoreCase("delete")) {
			response = given().spec(REQUEST_SPEC).delete(ENDPOINT);
		}else{
			System.out.println("this request type is not supported by the API");
		}
		response.then().log().all();
		response.then().spec(RESPONSE_SPEC);
		return response;
		
	}

	// transform the response to a jsonpath object
	public static JsonPath getJsonPath(Response res) {
		String path = res.asString();
		return new JsonPath(path);
	}
	
	// set the content type of a request 
	public static void setContentType(ContentType type) {
		given().contentType(type);
	}


}
