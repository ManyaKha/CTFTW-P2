package es.uc3m.tiw.domains;

import java.util.UUID;

public class Payment {
	
	private UUID transactionCode;
	private int price;
	private Card card;
	
	public Payment() {
		this.setTransactionCode(UUID.randomUUID());
	}
	
	public Payment(int price, Card card) {
		super();
		this.setPrice(price);
		this.setCard(card);
	}

	public UUID getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(UUID transactionCode) {
		this.transactionCode = transactionCode;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}
	
}
