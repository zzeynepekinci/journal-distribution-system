package proje;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class Distributor implements Serializable , Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final FileOutputStream yazici = null;
	private Hashtable<String,Journal> journals;
	private Vector<Subscriber> subscribers;
	public int report=0;
	public Distributor(Hashtable<String, Journal> journals, Vector<Subscriber> subscribers) {
		
		this.journals = new Hashtable<String, Journal>();
        this.subscribers = new Vector<Subscriber>();
	}
	public boolean addJournal(Journal journal) {
        if (!journals.containsKey(journal.getIssn())) {
            journals.put(journal.getIssn(), journal);
            return true;
        }
        return false;
    }
	public Journal searchJournal(String issn) {
        return journals.get(issn);
    }
	public boolean addSubscriber(Subscriber subscriber) {
        if (!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
            return true;
        }
        return false;
    }
	public Subscriber searchSubscriber(String name) {
		for(Subscriber sub :subscribers) {
			if(sub.getName().equalsIgnoreCase(name)) {
				return sub;
			}
			
		}
		return null;
	}
	
	
	public boolean addSubscription(String issn, Subscriber subscriber, Subscription subscription) {
        Journal journal = journals.get(issn);
        if (journal != null && !subscribers.contains(subscriber)) {
            journal.addSubscription(subscription);
            subscribers.add(subscriber);
            return true;
        }
        
        return false;
    }
	public Subscription searchSubscription(String name,String issn) {
		Journal journal = journals.get(issn);
	       
        if (journal != null) {
            
            ArrayList<Subscription> subscriptions = journal.getSubscriptions();
            
            if(!subscriptions.isEmpty()) {
            	
                for (Subscription s : subscriptions) {
                	if(s.getSubscriber().getName().equals(name)) {
        				return s;
        			}
                 }
                return null;
            }
            }
                   
         else {
            System.out.println("Journal with ISSN " + issn + " not found.");
          }
		return null;
       }
	
	
	public String listSendingOrders(String issn, int month, int year) {
        String s="";
		Journal journal = journals.get(issn);
       
        if (journal != null) {
           
            ArrayList<Subscription> subscriptions = journal.getSubscriptions();
            
            if(!subscriptions.isEmpty()) {
            	
                for (Subscription subscription : subscriptions) {
                DateInfo dates = subscription.getDates();
               
               if (year == dates.getStartYear() || dates.getStartYear()+1==year) {
                        if (subscription.canSend(month)) {
                        	s+="Sending Orders for ISSN: " + issn + ", Month: " + month + ", Year: " + year +"\n"+
                        	"Subscriber: "  + subscription.getSubscriber().getName() +
                                    ", Copies: " + subscription.getCopies() +
                                    ", Journal: " + journal.getName()+"\n";
                        }
                 }
            }
            }
                   
         else {
            s="Journal with ISSN " + issn + " not found." + "\n";
          }
       }
        return s;
	}
	public String listAllSendingOrders(int month,int year) {
		for(String key:journals.keySet()) {
			return listSendingOrders(key,month,year);
		}
		return null;
	}
	
	
	public String listIncompletePayments() {
		String s="";
		for(String key:journals.keySet()) {
			Journal journal = journals.get(key);
			
			if (journal != null) {
				 
				 for (Subscription subscription : journal.getSubscriptions()) {
					 
					 if (subscription != null && subscription.getSubscriber() != null) {
					     
						 if(subscription.getPayment().getReceivedPayment()<journal.getFrequency()*journal.getIssuePrice()*subscription.getCopies()) {
						     s+="Subscriber name: " + subscription.getSubscriber().getName() + " Journal: " + journal.getName()+ "\n";
					     }
				     }
				 }
			 }
		}
		return s;
	}	
		
		
	public String listSubscriptionsv1(String subscriberName) {
		String s="";
		s+=subscriberName + "'s journal(s)" + "\n";
		for(String key:journals.keySet()) {
			Journal journal = journals.get(key);
			 if (journal != null) {
				 for (Subscription subscription : journal.getSubscriptions()) {
					 if (subscription != null && subscription.getSubscriber() != null) {
					     if(subscription.getSubscriber().getName().equals(subscriberName)) {
						     s+=  journal.getName()+"\n";
					     }
				     }
				 }
			 }
		}
		return s;
	}
	public String listSubscriptionsv2(String issn) {
		String s="";
		Journal journal = journals.get(issn);
		if (journal != null) {
		s+= "Subscriptions for "+ journal.getName() + ":" + "\n" ;
		    for (Subscription subscription : journal.getSubscriptions()) {
				 if (subscription != null && subscription.getSubscriber() != null) {
				     s+=subscription.getSubscriber().getName()+"\n";
				     }
			}
	    }
		return s;
    }
	
    public synchronized void saveState(String fileName) {
    	
    
    	
    	 try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
    		 for(String key:journals.keySet()) {
    				Journal journal = journals.get(key);
    				 if (journal != null) {
    					 writer.write("Journal " + journal.getName()+" "  + journal.getIssn() + " "+ journal.getFrequency()+" "+ journal.getIssuePrice());
    					 writer.newLine();
    					 for (Subscription subscription : journal.getSubscriptions()) {
    						 if (subscription != null && subscription.getSubscriber() != null) {
    							 if(subscription.getSubscriber() instanceof Individual) {
    								 Individual i=(Individual)subscription.getSubscriber();
    								 writer.write("Individual"+ " " + i.getName()+ " "+ i.getAddress());
    								 writer.newLine();
    							 }	
    							 else if(subscription.getSubscriber() instanceof Corporation) {
    								 Corporation c =(Corporation)subscription.getSubscriber();
    								 writer.write("Corporation"+ " " +c.getName()+ " "+c.getAddress());
    								 writer.newLine();
    						    }
    				       }
    		            }
    				 }
    		 }
    	 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    					 
    }
    public synchronized void  loadState(String fileName) {
    	
    		 
    		 try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
    	            String line;
    	            while ((line = reader.readLine()) != null) {
    	                String[] parts = line.split(" ");
    	                if (parts[0].equals("Journal")) {
    	                	journals.put(parts[2], new Journal(parts[1],parts[2],Integer.parseInt(parts[3]),Double.parseDouble(parts[4])));
    	                }
    	                    
    	                 else if (parts[0].equals("Individual")) {
    	                    subscribers.add(new Individual(parts[1], parts[2]));
    	                }
    	                 else if (parts[0].equals("Corporation")) {
     	                    subscribers.add(new Corporation(parts[1], parts[2]));
     	                }
    	                
    	            }
    	        } catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
 
    	 }
    
    public String report() {
    	String s="";
    	int endyear=0,year=2026,month=3,y=2012,y2=2025;
    	s+="Subscriptions that will be expired after March 2026\n";
    	for(String key:journals.keySet()) {
			Journal journal = journals.get(key);
			
			if (journal != null) {
				 
				 for (Subscription subscription : journal.getSubscriptions()) {
					 
					 if (subscription != null && subscription.getSubscriber() != null) {
					     endyear=subscription.getDates().getStartYear()+1;
						 if(endyear < year || (endyear == year && subscription.getDates().getEndMonth() < month)) {
					    	 s+="name= " + subscription.getSubscriber().getName() + " "+"journal= "+ journal.getName()  + " end of subscripton: "+ subscription.getDates().getEndMonth()+ "/" + endyear+"\n";
					     }
						 
						 
				     }
					 
				 }
			 }
		}
    	s+="Received annual payments in 2012-2025\n ";
    	for(String key:journals.keySet()) {
			Journal journal = journals.get(key);
			
			if (journal != null) {
				 
				 for (Subscription subscription : journal.getSubscriptions()) {
					 
					 if (subscription != null && subscription.getSubscriber() != null) {
					     endyear=subscription.getDates().getStartYear()+1;
						
						 
						 if(subscription.getDates().getStartYear()>y && subscription.getDates().getStartYear()<y2) {
							 if(subscription.getPayment().getReceivedPayment()>0)
							     s+= "name= " + subscription.getSubscriber().getName()+ " "+" payment= "+ subscription.getPayment().getReceivedPayment() + "\n";
						 }
				     }
					 
				 }
			 }
		}
    	
    	return s;
    }
    public void run() {
    	report();
    }
    
    
    

    
		
}	


