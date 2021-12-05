package es.uc3m.tiw.domains;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Card {
	
	private String cardNumber;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date expirationDate;
	private int cv2;
	
	public Card() {
		
	}
	
	public Card(String cardNumber, Date expirationDate, int cv2) {
		this.setCardNumber(cardNumber);
		this.setExpirationDate(expirationDate);
		this.setCv2(cv2);
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getCv2() {
		return cv2;
	}

	public void setCv2(int cv2) {
		this.cv2 = cv2;
	}
	
	public boolean validateCard() {
		return this.validateNumber() && this.validateDate() && this.validateCv2();
	}
	
	public boolean validateNumber() {
		int numberLength = this.cardNumber.length();
		long reminder = Long.parseLong(this.cardNumber) % 3;
		return numberLength == 16 && reminder == 0;
	}
	
	public boolean validateDate() {
		return this.expirationDate.after(new Date());
	}
	
	public boolean validateCv2() {
		int cv2Length = String.valueOf(this.cv2).length();
		return cv2Length == 3;
	}
	

}
