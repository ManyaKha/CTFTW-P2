package es.uc3m.tiw.domains;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	String title;

	String owner;

	String category;
	
	int price;
	
	String status;
	
	public Product() {
		
	}
	
	public Product(String Title, String Owner, String Category, int Price, String Status) {
		super();
		this.title=Title;
		this.owner=Owner;
		this.category=Category;
		this.price=Price;
		this.status=Status;
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
		owner = owner;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		status = status;
	}	

}
