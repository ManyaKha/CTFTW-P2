package es.uc3m.tiw.domains;

public class Transaction {
	
	private String timeOfTransaction;
	private String productName;
	private int price;
	private String seller;
	private String buyer;
	
	public Transaction() {
		
	}
	
	public Transaction(String timeOfTransaction, String productName, int price, String seller,String buyer) {
		this.setTimeOfTransaction(timeOfTransaction);
		this.setProductName(productName);
		this.setPrice(price);
		this.setSeller(seller);
		this.setBuyer(buyer);
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
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
