package es.uc3m.tiw.domains;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	
	String title;

	String owner;

	String category;
	
	int price;
	
	String status;
	
	public Product() {
		
	}
	
	public Product(String title, String owner, String category, int price, String status) {
		super();
		this.title=title;
		this.owner=owner;
		this.category=category;
		this.price=price;
		this.status=status;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	

}
