package proje;

import java.io.Serializable;

public class Individual extends Subscriber implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String creditCardNr;
	private int expireMonth;
	private int expireYear;
	private int CCV;
	
	

	public Individual(String name, String address) {
		super(name,address);
	}


	

	@Override
	public String getBillingInformation() {
		return "Individual [creditCardNr=" + creditCardNr + ", expireMonth=" + expireMonth + ", expireYear="
				+ expireYear + ", CCV=" + CCV + "]";
	}




	public void setCreditCardNr(String creditCardNr) {
		this.creditCardNr = creditCardNr;
	}




	public void setExpireMonth(int expireMonth) {
		this.expireMonth = expireMonth;
	}




	public void setExpireYear(int expireYear) {
		this.expireYear = expireYear;
	}




	public void setCCV(int cCV) {
		CCV = cCV;
	}




	public String getCreditCardNr() {
		return creditCardNr;
	}




	public int getExpireMonth() {
		return expireMonth;
	}




	public int getExpireYear() {
		return expireYear;
	}




	public int getCCV() {
		return CCV;
	}
	



	
	
	

}
