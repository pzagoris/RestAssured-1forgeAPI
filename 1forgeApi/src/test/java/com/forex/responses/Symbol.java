package com.forex.responses;

//POJO representing a Symbol and some of its properties
public class Symbol extends ApiResponse{

	String symbol;

	// for (De)serializing POJOs in REST Assured, the class should contain getters and setters
		// for each field and an empty constructor 
    public Symbol() {      
    }
     
    public Symbol(String symbol) {
        this.symbol = symbol;
     }
 
    public String getSymbol() {
        return this.symbol;
    }
 
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    public String toString() {
        return "My values are " + this.symbol;
    }

 
}
