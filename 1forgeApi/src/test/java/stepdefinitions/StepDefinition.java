package stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.util.List;

import org.junit.*;

import com.forex.csv.OpenCsvWriter;
import com.forex.responses.ApiResponse;
import com.forex.restapi.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;


// the stepDefinitions for the feature file

public class StepDefinition {
	
	String symbolPairs;
	List<ApiResponse> listObj;
	
	private String getSymbolPairs()
	{
		return this.symbolPairs;
	}

	private List<ApiResponse> getListObj()
	{
		return listObj;
	}
	
	private void setSymbolPairs(String symbolPairs)
	{
		this.symbolPairs = symbolPairs;
	}
	
	private void setListObj(List<ApiResponse> listObj)
	{
		this.listObj=listObj;
	}
	
	
	@Given("^The market is open$")
	public void the_market_is_open()  {
	    // Write code here that turns the phrase above into concrete actions
		MarketStatusRequests p = new MarketStatusRequests();
		p.verifyMarketStatusIsOpen();
	}

	@Then("^I GET symbols$")
	public void i_GET_symbols()  {
	    // Write code here that turns the phrase above into concrete actions
		SymbolsRequests s = new SymbolsRequests();
		setSymbolPairs(s.GetSymbols());
	}

	@Then("^I GET (.+) pairs of quotes$")
	public void i_GET_pairs_of_quotes(String N_first) {
	    // Write code here that turns the phrase above into concrete actions
		QuotesRequests q = new QuotesRequests();
		
		
		if (N_first.toLowerCase().equals("all"))
			setListObj(q.GetQuotes(getSymbolPairs(), 0));
		else
			setListObj(q.GetQuotes(getSymbolPairs(), Integer.parseInt(N_first)));
			
	}

	@Then("^Write json response to (.+)$")
	public void write_json_response_to_file(String fileName) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
	    // Write code here that turns the phrase above into concrete actions
			OpenCsvWriter p = new OpenCsvWriter();
			p.writeFromListOfObjects(getListObj(), fileName);
			//p.writeFromListOfObjects(this.listObj);
		
	}



}
