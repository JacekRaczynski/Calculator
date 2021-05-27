/*
 * 
 * Currency
 * Parser XMLa
 * Version 1.0
 * Date: 26-05-2021
 * Autor: Jacek Raczyñski
 * Copyright CC0
 * 
 * Program generuje listê walut na podstawie pliku XML. 
 * Alfabetycznie sortuje listê walut.
 *
 */
package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class Currency {
	protected static ArrayList<String> currencyList;
	private static String date;
	private HashMap<String,Double> currency = new HashMap<>();
    private Map<String,String> tmpCube=null;
 
    
    public String getInputFileName() throws IOException {
		return ReadPropertyFile.getProperty("input_file_name");
	}
    public String getDate() {
		return date;
    }
	public void setDate(String date) {
		Currency.date = date;
	}
	public HashMap<String, Double> getCurrency() {
		return currency;
	}
	public void setCurrency(HashMap<String, Double> currency) {
		this.currency = currency;
	}
 

    public void ReadRecordsFromFile() throws Exception, IOException {
 	    SAXParserFactory saxDoc = SAXParserFactory.newInstance();
        SAXParser saxParser = saxDoc.newSAXParser();
        
        DefaultHandler handler = new DefaultHandler() {
        String tmpElementName = null;
        String tmpElementValue = null;

        public void startElement(String uri, String localName,
        		String qName, Attributes attributes) throws SAXException {
          tmpElementName = qName; 
          tmpElementValue = "";
          tmpCube=new HashMap();

          for (int i=0; i < attributes.getLength(); i++) {
              if(attributes.getLocalName(i) == "time") {
                  setDate(attributes.getValue(i));
               }
               String aname = attributes.getLocalName(i);
               String value = attributes.getValue(i);
               tmpCube.put(aname, value);  
          }
          
            if ((tmpCube.size()==2) && (tmpElementName == "Cube")){
            	if(Double.parseDouble(tmpCube.get("rate"))<0) {
            		throw new IllegalAccessError("The currency value cannot be negative");	
            	}
            	currency.put(tmpCube.get("currency"),
            			Double.parseDouble(tmpCube.get("rate")));
            }
           }
       };
        saxParser.parse(new File(getInputFileName()), handler);
        currencyList = new ArrayList<>(currency.keySet());
        currencyList.sort(null);              	   
    }
  }