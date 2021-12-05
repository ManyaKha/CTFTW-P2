package es.uc3m.tiw.domains;

import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long iduser;

	private String name;

	private String surname;
	
	@JsonIgnore
	@OneToMany (mappedBy="user",
				fetch = FetchType.EAGER)
	//@JoinColumn(name="usuarioId")
	Set<Address> address;	

	public User() {
	}

	public Long getIduser() {
		return this.iduser;
	}

	public void setIduser(Long iduser) {
		this.iduser = iduser;
	}

	public String getName() {
		return this.name;
	}

	public Set<Address> getAddress() {
		return this.address;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}