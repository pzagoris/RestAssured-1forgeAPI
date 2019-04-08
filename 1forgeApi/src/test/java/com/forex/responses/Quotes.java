package com.forex.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

// POJO representing a Quote and some of its properties
public class Quotes extends ApiResponse {
	
	@JsonProperty("symbol")
	String symbol;
	@JsonProperty("bid")
    double bid;
	@JsonProperty("ask")
    double ask;
	@JsonProperty("price")
	double price;
	@JsonProperty("timestamp")
    long timestamp;
   
    // for (De)serializing POJOs in REST Assured, the class should contain getters and setters
	// for each property and an empty constructor 
    public Quotes() {      
    }
     
    public Quotes(String symbol, double bid, double ask, double price, long timestamp) {
        this.symbol = symbol;
        this.bid = bid;
        this.ask = ask;
        this.price = price;
        this.timestamp = timestamp;

    }
 
    public String getSymbol() {
        return this.symbol;
    }
 
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
 
    public double getBid() {
        return this.bid;
    }
 
    public void setBid(double bid) {
        this.bid = bid;
    }
 
    
    public double getAsk() {
        return this.ask;
    }
 
    public void setAsk(double ask) {
        this.ask = ask;
    }
 
    public long getTimestamp() {
        return this.timestamp;
    }
 
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
     
    public double getPrice() {
        return this.price;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }
         
    public String toString() {
        return "My values are " + this.symbol + " " + this.bid + " " + this.ask+ " " +this.price + " " + this.timestamp;
    }

}
