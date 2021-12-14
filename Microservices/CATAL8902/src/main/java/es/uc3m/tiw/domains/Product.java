package es.uc3m.tiw.domains;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	String id;
	String title;
	String owner;
	String category;
	String description;
	String image;
	float price;
	String status;
	
	public Product() {
		
	}
	
	public Product(String id, String title, String owner, String category, String description, float price, String status) {
		super();
		this.id = id;
		this.title=title;
		this.owner=owner;
		this.category=category;
		this.description=description;
		this.price=price;
		this.status=status;
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	

}
