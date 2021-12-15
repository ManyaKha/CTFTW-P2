package es.uc3m.tiw.domains;

public class Transaction {
	
	private String transactionId;
	private String timeOfTransaction;
	private String productName;
	private float price;
	private String seller;
	private String buyer;
	
	public Transaction() {
		
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTimeOfTransaction() {
		return timeOfTransaction;
	}

	public void setTimeOfTransaction(String timeOfTransaction) {
		this.timeOfTransaction = timeOfTransaction;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

}
