package edu.sabanciuniv.sibeldy.termproj.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Shop implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String shopName;
	private String email;
	private String password;
	private String address;
	private String telephone;
	private double longitude;
	private double latitude;

	
	@ManyToOne
	private Category category;
	
	@OneToOne
	private City city;

	@OneToOne
	private District district;

	@OneToMany(mappedBy = "shop")
	private List<Advertisement> advertisement;

	@OneToMany(mappedBy = "shop")
	private List<User> users;

	public Shop() {
		super();
	}

	public Shop(String shopName, String email, String password, String address, String telephone, Category category,
			double longitude, double latitude, City city, District district) {
		super();
		this.shopName = shopName;
		this.email = email;
		this.password = password;
		this.address = address;
		this.telephone = telephone;
		this.category = category;
		this.longitude = longitude;
		this.latitude = latitude;
		this.city = city;
		this.district = district;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public List<Advertisement> getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(List<Advertisement> advertisement) {
		this.advertisement = advertisement;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

}
