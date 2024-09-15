package proje;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Hashtable;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

public class Nyp {

	
	
	
	
	
	@org.junit.Test
	public void testSetAndGetStartMonth() {
        DateInfo dateInfo = new DateInfo();
        dateInfo.setStartMonth(5);
        assertEquals(5, dateInfo.getStartMonth());
    }
	@org.junit.Test
    public void testSetAndGetEndMonth() {
        DateInfo dateInfo = new DateInfo();
        dateInfo.setEndMonth(8);
        assertEquals(8, dateInfo.getEndMonth());
    }

	@org.junit.Test
    public void testSetAndGetStartYear() {
        DateInfo dateInfo = new DateInfo();
        dateInfo.setStartYear(2022);
        assertEquals(2022, dateInfo.getStartYear());
    }
	@org.junit.Test
	public void testIncreasePayment() {
        PaymentInfo paymentInfo = new PaymentInfo(0.1, 50.0);
        paymentInfo.increasePayment(30.0);
        assertEquals(80.0, paymentInfo.getReceivedPayment(), 0.001);
    }

	@org.junit.Test
    public void testGetReceivedPayment() {
        PaymentInfo paymentInfo = new PaymentInfo(0.05, 100.0);
        assertEquals(100.0, paymentInfo.getReceivedPayment(), 0.001);
    }

	@org.junit.Test
    public void testGetDiscountRatio() {
        PaymentInfo paymentInfo = new PaymentInfo(0.15, 200.0);
        assertEquals(0.15, paymentInfo.getDiscountRatio(), 0.001);
    }
	

	 @org.junit.Test
	 public void testToString() {
		Subscription subscription;
		 DateInfo dates;
		 Journal journal;
		 Subscriber subscriber;
		 PaymentInfo payment;
		 payment=new PaymentInfo(0.0,0.0);
		 
		 dates = new DateInfo();
	     dates.setStartMonth(1);
	     dates.setEndMonth(12);
	     dates.setStartYear(2022);

	     journal = new Journal("Sample Journal", "1254", 6,10.0);

	     subscriber = new Individual("John Doe", "john@example.com");

	     subscription = new Subscription(dates, 2, journal, subscriber,payment);
	     subscription.acceptPayment(40.0);
		String expectedString = "Subscription [dates=" + dates + ", copies=" + 2 + ", journal=" + journal
                + ", subscriber=" + subscriber + "]";
        assertEquals(expectedString, subscription.toString());
     }
	 @org.junit.Test
	 public void testGetBillingInformation() {
		 Individual individual;
		 individual = new Individual("John Doe", "123 Main St");
	     individual.setCreditCardNr("1234567890123456");
	     individual.setExpireMonth(12);
	     individual.setExpireYear(2025);
	     individual.setCCV(123);  
		 
		 String expectedBillingInfo = "Individual [creditCardNr=1234567890123456, expireMonth=12, expireYear=2025, CCV=123]";
	        assertEquals(expectedBillingInfo, individual.getBillingInformation());
	    }
	 @org.junit.Test
	 public void testSetAndGetCreditCardNr() {
		 Individual individual;
		 individual = new Individual("John Doe", "123 Main St");
	     individual.setCreditCardNr("1234567890123456");
	     individual.setExpireMonth(12);
	     individual.setExpireYear(2025);
	     individual.setCCV(123);    
		 individual.setCreditCardNr("9876543210987654");
	        assertEquals("9876543210987654", individual.getCreditCardNr());
	    }

	 @org.junit.Test
	    public void testSetAndGetExpireMonth() {
		 Individual individual;
		 individual = new Individual("John Doe", "123 Main St");
	     individual.setCreditCardNr("1234567890123456");
	     individual.setExpireMonth(12);
	     individual.setExpireYear(2025);
	     individual.setCCV(123);    
		 individual.setExpireMonth(6);
	        assertEquals(6, individual.getExpireMonth());
	    }

	 @org.junit.Test
	    public void testSetAndGetExpireYear() {
		 Individual individual;
		 individual = new Individual("John Doe", "123 Main St");
	     individual.setCreditCardNr("1234567890123456");
	     individual.setExpireMonth(12);
	     individual.setExpireYear(2025);
	     individual.setCCV(123);    
		 individual.setExpireYear(2030);
	        assertEquals(2030, individual.getExpireYear());
	    }

	 @org.junit.Test
	    public void testSetAndGeCCV() {
		 Individual individual;
		 individual = new Individual("John Doe", "123 Main St");
	     individual.setCreditCardNr("1234567890123456");
	     individual.setExpireMonth(12);
	     individual.setExpireYear(2025);
	     individual.setCCV(123);    
		 individual.setCCV(456);
	        assertEquals(456, individual.getCCV());
	    }
	 @org.junit.Test
	 public void testGetName() {
		     Individual individual;
		     individual = new Individual("John Doe", "123 Main St");  
		     assertEquals("John Doe", individual.getName());
	    }

	 @org.junit.Test
	    public void testGetAddress() {
		     Individual individual;
	         individual = new Individual("John Doe", "123 Main St"); 
		     assertEquals("123 Main St", individual.getAddress());
	    }
	 @org.junit.Test
	 public void testAddSubscription() {
		    Journal journal;
		    journal = new Journal("Sample Journal", "1254", 6, 10.0);
		    PaymentInfo payment;
			payment=new PaymentInfo(0.0,0.0);
		    Subscription subscription = new Subscription(new DateInfo(), 2, journal, new Individual("John Doe", "john@example.com"),payment);
	        journal.addSubscription(subscription);

	        
	        assertTrue(journal.getSubscriptions().contains(subscription));
	    }
	 @org.junit.Test
	 public void testGetIssuePrice() {
		    Journal journal;
		    journal = new Journal("Sample Journal", "1254", 6, 10.0);
		    PaymentInfo payment;
			payment=new PaymentInfo(0.0,0.0);   
		    assertEquals(10.0, journal.getIssuePrice(), 0.001);
	    }

	 @org.junit.Test
	    public void testGetFrequency() {
	    	Journal journal;
		    journal = new Journal("Sample Journal", "1254", 6, 10.0);
		    PaymentInfo payment;
			payment=new PaymentInfo(0.0,0.0);
	    	assertEquals(6, journal.getFrequency());
	    }

	 @org.junit.Test
	    public void testGetIssn() {
	    	Journal journal;
		    journal = new Journal("Sample Journal", "1254", 6, 10.0);
		    PaymentInfo payment;
			payment=new PaymentInfo(0.0,0.0);
	    	assertEquals("1254", journal.getIssn());
	    }

	 @org.junit.Test
	    public void testGetJournalName() {
	    	Journal journal;
		    journal = new Journal("Sample Journal", "1254", 6, 10.0);
		    PaymentInfo payment;
			payment=new PaymentInfo(0.0,0.0);
	    	assertEquals("Sample Journal", journal.getName());
	    }
	
	


    
	
	 @org.junit.Test
	 public void testAddJournal() {
		    Distributor distributor;
		    distributor = new Distributor(new Hashtable<>(), new Vector<>());
		    Journal journal = new Journal("Test Journal", "1234", 6, 15.0);
	        assertTrue(distributor.addJournal(journal));
	        assertFalse(distributor.addJournal(journal));
	    }
	 @org.junit.Test
	 public void testSearchJournal() {
		 Distributor distributor;
		    distributor = new Distributor(new Hashtable<>(), new Vector<>());
		    Journal journal = new Journal("Test Journal", "1234", 6, 15.0);  
		 assertNull(distributor.searchJournal("1234"));

	        
	        distributor.addJournal(journal);

	        assertEquals(journal, distributor.searchJournal("1234"));
	    }
	 @org.junit.Test
	 public void testAddSubscriber() {
		 Distributor distributor;
		    distributor = new Distributor(new Hashtable<>(), new Vector<>());
		 Subscriber subscriber = new Individual("John Doe", "123 Main St");
	        assertTrue(distributor.addSubscriber(subscriber));
	        assertFalse(distributor.addSubscriber(subscriber));
	    }
	 @org.junit.Test
	 public void testSearchSubscriber() {
		    Distributor distributor;
		    distributor = new Distributor(new Hashtable<>(), new Vector<>());
		    assertNull(distributor.searchSubscriber("John Doe"));

	        Subscriber subscriber = new Individual("John Doe", "123 Main St");
	        distributor.addSubscriber(subscriber);

	        assertEquals(subscriber, distributor.searchSubscriber("John Doe"));
	    }
	 @org.junit.Test
	 public void testAddSubscription2() {
		    Distributor distributor;
		    distributor = new Distributor(new Hashtable<>(), new Vector<>());
		    PaymentInfo payment;
		    payment=new PaymentInfo(0.0,0.0);
		    Journal journal = new Journal("Test Journal", "1234", 6, 15.0);
	        Subscriber subscriber = new Individual("John Doe", "123 Main St");
	        Subscription subscription = new Subscription(new DateInfo(), 2, journal, subscriber,payment);

	        assertFalse(distributor.addSubscription("1234", subscriber, subscription));
	        distributor.addJournal(journal);
	        assertTrue(distributor.addSubscription("1234", subscriber, subscription));
	    }
	
	 
	
	 
	 
	 

}
