package es.uc3m.tiw.domains;

public class CreditCard {
	private String cardNumber;
	private String expirationDate;
	private int cv2;
	
	
	public CreditCard() {
		
	}
	
	public CreditCard(String cardNumber, String expirationDate, String cv2) {
		this.setCardNumber(cardNumber);
		this.setExpirationDate(expirationDate);
		this.setCv2(Integer.parseInt(cv2));
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getCv2() {
		return cv2;
	}

	public void setCv2(int cv2) {
		this.cv2 = cv2;
	}
}
