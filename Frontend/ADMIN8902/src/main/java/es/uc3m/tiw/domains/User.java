package es.uc3m.tiw.domains;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private String name;
	private String surname;
	private String city;
	private boolean administrator;
	private boolean current;
	
	public User() {
	}
	
	public User(String email, String password, String surname, String city, boolean administrator) {
		this.setEmail(email);
		this.setPassword(password);
		this.setSurname(surname);
		this.setCity(city);
		this.setAdministrator(administrator);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean isAdministrator() {
		return administrator;
	}

	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}

	public boolean isCurrent() {
		return current;
	}

	public void setCurrent(boolean current) {
		this.current = current;
	}
}
