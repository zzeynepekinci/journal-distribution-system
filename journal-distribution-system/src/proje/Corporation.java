package proje;

import java.io.Serializable;

public class Corporation extends Subscriber implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int bankCode;
	private String bankName;
	private int issueDay;
	private int issueMonth;
	private int issueYear;
	private int accountNumber;
	public Corporation(String name, String address) {
		super(name, address);
		this.bankCode = bankCode;
		this.bankName = bankName;
		this.issueDay = issueDay;
		this.issueMonth = issueMonth;
		this.issueYear = issueYear;
		this.accountNumber = accountNumber;
	}
	@Override
	public String getBillingInformation() {
		return "Corporation [bankCode=" + bankCode + ", bankName=" + bankName + ", issueDay=" + issueDay
				+ ", issueMonth=" + issueMonth + ", issueYear=" + issueYear + ", accountNumber=" + accountNumber
				+ ", Name=" + getName() + ", Address=" + getAddress() + "]";
	}
	public int getIssueMonth() {
		return issueMonth;
	}
	
	

}
