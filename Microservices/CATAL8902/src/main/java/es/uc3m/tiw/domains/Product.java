package es.uc3m.tiw.domains;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String Title;

	private String Owner;

	private String Category;
	
	private int Price;
	
	private String Status;
	
	public Product() {
		
	}
	
	public Product(String Title, String Owner, String Category, int Price, String Status) {
		super();
		this.Title=Title;
		this.Owner=Owner;
		this.Category=Category;
		this.Price=Price;
		this.Status=Status;
	}

	public String getTitle() {
		return this.Title;
	}

	public void setTitle(String title) {
		this.Title = title;
	}

	public String getOwner() {
		return Owner;
	}

	public void setOwner(String owner) {
		Owner = owner;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}	

}
