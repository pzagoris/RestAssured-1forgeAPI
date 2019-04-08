package com.forex.csv;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.forex.responses.*;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

// OpenCsvWriter class for writing to csv files
public class OpenCsvWriter {
	   
	   public OpenCsvWriter() {}
	   
	   // wrtiteFromListOfObject: this method taks as input a list of java plain objects and write them in a csv fileName
	   public void writeFromListOfObjects(List<ApiResponse> allobj, String fileName) throws IOException,CsvDataTypeMismatchException,CsvRequiredFieldEmptyException {

		   try (
				   Writer writer = Files.newBufferedWriter(Paths.get("./"+fileName));
		   ) 
		   {
			   StatefulBeanToCsv<ApiResponse> beanToCsv = new StatefulBeanToCsvBuilder(writer)
	                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
	                    .build();
			   
			    beanToCsv.write(allobj);

		   }
		   }


}
