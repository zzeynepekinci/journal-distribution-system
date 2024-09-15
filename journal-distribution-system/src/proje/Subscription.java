package proje;

import java.io.Serializable;

public class Subscription implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final DateInfo dates;
	private PaymentInfo payment;
	private int copies;
	private final Journal journal;
	private final Subscriber subscriber;
	public Subscription(DateInfo dates, int copies, Journal journal, Subscriber subscriber,PaymentInfo payment) {
		super();
		this.dates = dates;
		this.copies = copies;
		this.journal = journal;
		this.subscriber = subscriber;
		this.payment = new PaymentInfo(0.0, 0.0);
	}
	public void acceptPayment(double amount) {
		getPayment().increasePayment(amount);
	}	

	public boolean canSend(int issueMonth) {
	    int monthcount = 0;
	   
	    if (issueMonth >= getDates().getStartMonth()) {
	        monthcount = issueMonth - getDates().getStartMonth() ;
	    } else {
	        monthcount = issueMonth + (12 - getDates().getStartMonth()) + 1;
	    }
	    
	    int amount = (monthcount * getJournal().getFrequency()) / 12;
	    double price = getJournal().getIssuePrice() * getCopies() * amount;

	    return getPayment().getReceivedPayment() >= price;
	}

	@Override
	public String toString() {
		return "Subscription [dates=" + this.getDates() + ", copies=" + copies + ", journal=" + this.getJournal() + ", subscriber="
				+ subscriber + "]";
	}
	public Subscriber getSubscriber() {
		return subscriber;
	}
	public int getCopies() {
		return copies;
	}
	public DateInfo getDates() {
		return dates;
	}
	public PaymentInfo getPayment() {
		return payment;
	}
	public Journal getJournal() {
		return journal;
	}
	

}
