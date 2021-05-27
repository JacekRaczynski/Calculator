package test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import main.Currency;
import main.ReadPropertyFile;

class CurrencyTest {

	 
	@Test
	public void getProperty_name_resultTrue() throws IOException {
		Assert.assertEquals("Calculator", ReadPropertyFile.getProperty("name"));
	}
	
	@Test
	public void getInputFileName_inputFileName_resultTrue() throws IOException {
		//given
		Currency currency = new Currency();
		Pattern pattern = Pattern.compile("eurofxref-daily.xml");
		Matcher matcher = pattern.matcher(currency.getInputFileName());
		//then    
		Assert.assertTrue(matcher.find());
	}
	
	@Test
	public void ReadRecordsFromFile_time_resultTrue() {
		//given
		Currency currency = new Currency();
		try {
			currency.ReadRecordsFromFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 Pattern pattern = Pattern.compile(
				 "([0-9]{4})[-]([0][1-9]|[1][0-2])[-]([0-2][0-9]|[3][0|1])");
		 Matcher matcher = pattern.matcher(currency.getDate());
		
		//then
		 Assert.assertTrue(matcher.find());
	}
	
	@Test
	public void  ReadRecordsFromFile_currencyLocalName_resultTrue() {
		Currency currency = new Currency();
		try {
			currency.ReadRecordsFromFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(String i : currency.getCurrency().keySet())
		{
			Assert.assertEquals(3,i.length());
		}
	}
	
	@Test
	public void  ReadRecordsFromFile_currencyValue_resultTrue() {
		Currency currency = new Currency();
		try {
			currency.ReadRecordsFromFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(Double i : currency.getCurrency().values())
		{
			Assert.assertTrue(i != 0.0);
			Assert.assertNotNull(i);
		}
	}
	
	
	
	
}
