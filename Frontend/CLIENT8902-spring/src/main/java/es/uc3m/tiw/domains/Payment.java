package es.uc3m.tiw.domains;

public class Payment {
	
	private float price;
	private CreditCard card;
	
	public Payment() {
		
	}
	
	public Payment(float price, CreditCard card) {
		this.price = price;
		this.card = card;
	}
	
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public CreditCard getCard() {
		return card;
	}
	
	public void setCard(CreditCard card) {
		this.card = card;
	}
	
	

}
