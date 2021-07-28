package com.vaccination.app.models;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.*;

@Entity
public class VaccinationCentre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // Let Hibernate decide how this value is to be generated
	private long id;
	
	@Column(unique=true,nullable=false)
	private String license;
	
	@Column
	private String name;
	
	@Column
	private String address;
	
	@Column
	private String pin_code;
	
	@Column
	private int user_count;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getUser_count() {
		return user_count;
	}

	public void setUser_count(int user_count) {
		this.user_count = user_count;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@OneToMany(mappedBy = "centre",fetch = FetchType.LAZY)
	List<User> users = new ArrayList<User>();
	
	
	public String getLicense() {
		return license;
	}

	public void setLicense(String license_number) {
		this.license = license_number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPin_code() {
		return pin_code;
	}

	public void setPin_code(String pin_code) {
		this.pin_code = pin_code;
	}
}
