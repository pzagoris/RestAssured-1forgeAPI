package com.forex.restapi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pzagoris.utilities.RestUtilities;
import com.forex.constants.Path;
import com.forex.responses.Symbol;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
/*
 * SymbolsRequests: class that contains all the requests regarding the Symbols basic path
 * 
 */
public class SymbolsRequests {
	
	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	
	public SymbolsRequests() {}
	
	public String GetSymbols() {
					
		reqSpec = RestUtilities.getRequestSpecification();
		reqSpec.basePath(Path.SYMBOLS);	
		resSpec = RestUtilities.getResponsepecification();

		Response response =  given().spec(reqSpec).when().get().then().spec(resSpec).extract().response();
		
		//because the result is a an array of strings, clean it accordingly
		String res = ParseSymbols(response);
		return res;
	}

	private String ParseSymbols(Response resp) {

		String res = resp.getBody().asString();
		res = res.replace("\"", "").replace("[", "").replace("]","");
		return res;
	}

	
}


