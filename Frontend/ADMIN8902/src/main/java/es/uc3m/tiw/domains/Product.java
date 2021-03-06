package es.uc3m.tiw.domains;

import java.io.Serializable;


public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String id;
	String title;
	String owner;
	String category;
	String description;
	float price;
	String status;
	
	public Product() {
	}
	
	public Product(String id, String title, String owner, String category, String description, float price, String status) {
		this.id = id;
		this.title = title;
		this.owner = owner;
		this.category = category;
		this.description = description;
		this.price = price;
		this.status = status;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}		
	
	
}
