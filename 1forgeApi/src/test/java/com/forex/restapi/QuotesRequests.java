package com.forex.restapi;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;

import com.pzagoris.utilities.RestUtilities;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import com.forex.constants.Path;
import com.forex.responses.*;

/*
 * QuotesRequests: class that contains all the requests for the Quotes basic path
 * 
 */
public class QuotesRequests {
	
	ResponseSpecification resSpec;
	RequestSpecification reqSpec;

	public QuotesRequests() {}

	public List<ApiResponse> GetQuotes(String pairs, int N_first){

		reqSpec = RestUtilities.getRequestSpecification();
		reqSpec.basePath(Path.QUOTES);	
		resSpec = RestUtilities.getResponsepecification();
		if (N_first>0) {
			pairs = GetFirstNPairs(pairs, N_first);
			//System.out.println(pairs);
		}
		reqSpec = RestUtilities.createQueryParam(reqSpec, "pairs", pairs);

		
		Response response =  given().spec(reqSpec).when().get().then().spec(resSpec).extract().response();
		
		//System.out.println(response.asString());

		//Deserialization
		//using the opencsv library we can write the json result to a csv file
		List<ApiResponse> allobj = (List<ApiResponse>)(List<?>)response.jsonPath().getList("", Quotes.class);
		return allobj;
		
	}
	

	private String GetFirstNPairs(String pairs, int N) {
			int pos = (6*N) + (N-1);
			pairs = pairs.substring(0, pos);
			return pairs;
	}

	
}

