package es.uc3m.tiw.domains;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@SuppressWarnings("serial")
@Document(collection="Transactions")
@JsonPropertyOrder({"_id", "timeOfTransaction", "productName", "price", "seller", "buyer"})
public class Transaction implements Serializable{
	
	@Id
	private String id;
	private String timeOfTransaction;
	private String productName;
	private int price;
	private String seller;
	private String buyer;
	
	public Transaction() {
		
	}
	
	@PersistenceConstructor
	public Transaction(String id, String timeOfTransaction, String productName, int price, String seller,String buyer) {
		this.id = id;
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
