package proje;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Journal implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String issn;
	private int frequency;
	private double issuePrice;;
	private ArrayList<Subscription> subscriptions;
	
	
	
	public Journal(String name, String issn, int frequency, double issuePrice) {
		super();
		this.name = name;
		this.issn = issn;
		this.frequency = frequency;
		this.issuePrice = issuePrice;
		this.subscriptions = new ArrayList<>();
		
	}
	public  void addSubscription(Subscription s) {
		if(subscriptions.contains(s)==false) {
			subscriptions.add(s);
			
		}
		
		
	}
	public Subscription searchSubscription(String name) {
		for(Subscription s:subscriptions) {
			if(s.getSubscriber().getName().equals(name)) {
				return s;
			}
		}
		return null;
	}
	public double getIssuePrice() {
		return issuePrice;
	}
	public int getFrequency() {
		return frequency;
	}
	public String getIssn() {
		return issn;
	}
	public  ArrayList<Subscription> getSubscriptions() {
		return subscriptions;
	}
	public String getName() {
		return name;
	}
	 
	
	
	
	

}
