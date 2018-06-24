package netbanking.pojo.dao;

public class Transaction {
	private long accountNumber;
	private String name;
	private long destAccountNumber;
	private String destAccountName;
	private String bankName;
	private String ifsc;
	private String date;
	private String description;
	private double ammount;

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public long getDestAccountNumber() {
		return destAccountNumber;
	}

	public void setDestAccountNumber(long destAccountNumber) {
		this.destAccountNumber = destAccountNumber;
	}

	public String getDestAccountName() {
		return destAccountName;
	}

	public void setDestAccountName(String destAccountName) {
		this.destAccountName = destAccountName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmmount() {
		return ammount;
	}

	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
