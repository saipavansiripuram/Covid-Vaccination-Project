package com.vaccination.app.models;
import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column
	private String name;
	
	@Column(unique=true,nullable=false)
	private String aadhaar;
	
	@Column
	private String password;
	
	@Column
	private int age;
	
	@Column
	private String status;
	
	@OneToOne(cascade=CascadeType.MERGE, fetch = FetchType.EAGER) // Update vaccination centre's count on user update
	@JoinColumn(name="vacc_centre")
	private VaccinationCentre centre;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public VaccinationCentre getCentre() {
		return centre;
	}
	public void setCentre(VaccinationCentre centre) {
		this.centre = centre;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAadhaar() {
		return aadhaar;
	}
	public void setAadhaar(String aadhaar_number) {
		this.aadhaar = aadhaar_number;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "User["+name+", "+age+", "+aadhaar+", "+status+"]";
	}
}
