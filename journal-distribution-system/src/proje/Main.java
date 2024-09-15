package proje;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Main  {
	private static Distributor distributor;
	public static void main(String[] args) {
		
		
	
		
		distributor = new Distributor(new Hashtable<>(), new Vector<>());
		
       
       
        JFrame frame = new JFrame("Subscription Management System");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
       
        
        JButton addJournalButton = new JButton("Add Journal");
        JButton addSubscriberButton = new JButton("Add Subscriber");
        JButton addSubscriptionButton = new JButton("Add Subscription");
        JButton acceptPaymentButton = new JButton("Accept Payment");
        JButton listSendingOrdersButton = new JButton("List Sending Orders");
        JButton listAllSendingOrdersButton = new JButton("List All Sending Orders");
        JButton listIncompletePaymentsButton = new JButton("List Incomplete Payments");
        JButton listSubscriptions1Button = new JButton("List Subscriptions By Name");
        JButton listSubscriptions2Button = new JButton("List Subscriptions By Issn");
        JButton loadStateButton = new JButton("Load State");
        JButton saveStateButton = new JButton("Save State");
        JButton reportButton = new JButton("Generate Report");
       
        addJournalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String name = JOptionPane.showInputDialog(frame, "Enter Journal Name:");
                String issn = JOptionPane.showInputDialog(frame, "Enter Journal ISSN:");
                int frequency = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Journal Frequency:"));
                double issuePrice = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter Journal Issue Price:"));

               
                Journal journal = new Journal(name, issn, frequency, issuePrice);
                distributor.addJournal(journal);

                JOptionPane.showMessageDialog(frame, "Journal added successfully!");
            }
        });

        addSubscriberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                Object[] options = {"Individual", "Corporation"};
                int choice = JOptionPane.showOptionDialog(frame,
                        "Choose Subscriber Type:",
                        "Subscriber Type",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

               
                if (choice == JOptionPane.YES_OPTION) {
                    
                    String name = JOptionPane.showInputDialog(frame, "Enter Individual Name:");
                    String address = JOptionPane.showInputDialog(frame, "Enter Individual Address:");
                    String creditCardNr = JOptionPane.showInputDialog(frame, "Enter Credit Card Number:");
                    int expireMonth = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Credit Card Expiry Month:"));
                    int expireYear = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Credit Card Expiry Year:"));
                    int cCV = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Credit Card CCV:"));

                    Individual individual = new Individual(name, address);
                    individual.setCreditCardNr(creditCardNr);
                    individual.setExpireMonth(expireMonth);
                    individual.setExpireYear(expireYear);
                    individual.setCCV(cCV);

                    distributor.addSubscriber(individual);
                } else if (choice == JOptionPane.NO_OPTION) {
                    
                    String name = JOptionPane.showInputDialog(frame, "Enter Corporation Name:");
                    String address = JOptionPane.showInputDialog(frame, "Enter Corporation Address:");
                   

                    Corporation corporation = new Corporation(name, address);
                    distributor.addSubscriber(corporation);
                } else {
                    
                    return;
                }

                JOptionPane.showMessageDialog(frame, "Subscriber added successfully!");
            }
        });

        addSubscriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String issn = JOptionPane.showInputDialog(frame, "Enter Journal ISSN:");
                String subscriberName = JOptionPane.showInputDialog(frame, "Enter Subscriber Name:");
                int copies = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Number of Copies:"));
                int startMonth = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Start Month:"));
                int startYear = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Start Year:"));

                Journal journal = distributor.searchJournal(issn);
                Subscriber subscriber = distributor.searchSubscriber(subscriberName);

              
                if (journal != null && subscriber != null) {
              
                    PaymentInfo paymentInfo = new PaymentInfo(0.0, 0.0);
                    DateInfo dateInfo = new DateInfo();
                    dateInfo.setStartMonth(startMonth);
                    dateInfo.setStartYear(startYear);
                    dateInfo.setEndMonth(startMonth);

                   
                    Subscription subscription = new Subscription(dateInfo, copies, journal, subscriber, paymentInfo);
                    distributor.addSubscription(issn, subscriber, subscription);
                    journal.addSubscription(subscription);

                    JOptionPane.showMessageDialog(frame, "Subscription added successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Journal or subscriber not found!");
                }
            }
            
        });       

        listSendingOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String issn = JOptionPane.showInputDialog(frame, "Enter Journal ISSN:");
            	int month = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter a Month:"));
            	int year = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter a Year:"));
            	String s=distributor.listSendingOrders(issn,month,year);
            	JOptionPane.showMessageDialog(null, s, "Subscriptions List", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        listAllSendingOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int year = Integer.parseInt(JOptionPane.showInputDialog("Enter a Year:"));
                int month = Integer.parseInt(JOptionPane.showInputDialog("Enter a Month:"));

                String s;
                s=distributor.listAllSendingOrders(month, year);
                JOptionPane.showMessageDialog(null, s, "Subscriptions List", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        listIncompletePaymentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s;
                s=distributor.listIncompletePayments();
                JOptionPane.showMessageDialog(null, s, "Subscriptions List", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        listSubscriptions1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
            	String subscriberName = JOptionPane.showInputDialog(null, "Enter Subscriber Name:");
                String s=distributor.listSubscriptionsv1(subscriberName);
                JOptionPane.showMessageDialog(null, s, "Subscriptions List", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        listSubscriptions2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            	
                String issn = JOptionPane.showInputDialog(null, "Enter Journal ISSN:");

                // listSubscriptionsv2 metodunu çağır
                String subscriptionsList = distributor.listSubscriptionsv2(issn);

                // Sonuçları kullanıcıya göster
                JOptionPane.showMessageDialog(null, subscriptionsList, "Subscriptions List", JOptionPane.INFORMATION_MESSAGE);
                
            }
        });
        
        
        
        acceptPaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                String name = JOptionPane.showInputDialog(frame, "Enter Subscription name");
                String issn = JOptionPane.showInputDialog(frame, "Enter journal issn");
                double paymentAmount = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter Payment Amount:"));

                
                Subscription subscription = distributor.searchSubscription(name,issn);

                if (subscription != null) {
                    
                    subscription.acceptPayment(paymentAmount);
                    JOptionPane.showMessageDialog(frame, "Payment accepted successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Subscription not found!");
                }
            }
        });
        
        saveStateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String fileName = JOptionPane.showInputDialog(frame, "Enter File Name:");

                
                distributor.saveState(fileName);
                JOptionPane.showMessageDialog(frame, "State saved successfully!");
            }
        });
        
        loadStateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String fileName = JOptionPane.showInputDialog(frame, "Enter File Name:");

                
                distributor.loadState(fileName);
                JOptionPane.showMessageDialog(frame, "State loaded successfully!");
            }
        });
    
        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Thread t = new Thread(distributor);
            	if (!t.isAlive()) {  
                   t.start();  
                    try {
                        t.join();  
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            	 String report = distributor.report();
                 JOptionPane.showMessageDialog(null, report, "Report", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        
        panel.add(acceptPaymentButton);
        panel.add(saveStateButton);
        panel.add(loadStateButton);
        panel.add(listAllSendingOrdersButton);
        panel.add(listIncompletePaymentsButton);
        panel.add(listSubscriptions1Button);
        panel.add(listSubscriptions2Button);
        panel.add(reportButton);
        panel.add(addJournalButton);
        panel.add(addSubscriberButton);
        panel.add(addSubscriptionButton);
        panel.add(listSendingOrdersButton);

        
        frame.add(panel);

        frame.setVisible(true);
    }
	
	}


