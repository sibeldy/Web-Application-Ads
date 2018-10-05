package edu.sabanciuniv.sibeldy.termproj.mbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import edu.sabanciuniv.sibeldy.termproj.business.UserService;
import edu.sabanciuniv.sibeldy.termproj.domain.Advertisement;
import edu.sabanciuniv.sibeldy.termproj.domain.Category;
import edu.sabanciuniv.sibeldy.termproj.domain.City;
import edu.sabanciuniv.sibeldy.termproj.domain.Distance;
import edu.sabanciuniv.sibeldy.termproj.domain.Shop;

@ManagedBean
@ViewScoped
public class ListAdsBean implements Serializable {
	
	@EJB
	private UserService userService;
	
	private String productName;
	private String description;
	private String pictureName;
	private Date start;
	private Date end;
	private List<Category> categories;
	private Category category;
	private Distance distance;
	private List<Distance> distances;
	private List<Advertisement> ads;
	private double longitude;
	private double latitude;
	private Date today;
	
	private String userName;
	private String userEmail;
	private Category userCategory;
	private Shop shop;
	private City userCity; 
	private List<Shop> shops;
	private List<City> cities; 
	
	
	@PostConstruct
	private void init() {
		today = new Date();
		categories = userService.listCategories();
		distances = userService.listDistances();
	    this.ads = userService.listAll(today);
	    shops = userService.listShops();
	    cities = userService.listCities();    	    
	}
	
	public List<Advertisement> listSelectedAds() {
		
		today = new Date();

		if(category==null && distance==null ) {
			
			ads=userService.listAll(today);
			return ads;
		}
		else if(category!=null && distance == null) {
			
			ads = userService.selectByCategory(category, today); 
			return ads;		
		}
		else if (category==null && distance!=null) {	
			
			ads = userService.selectByDistance(category, distance , latitude, longitude, today);	
			return ads;
		}	
		else if (category!=null && distance!=null){	
			
			ads = userService.selectByDistance(category, distance , latitude, longitude, today);	
			return ads;
		}		
		else {
			return null;
		}
	}
	
	
	public void registerUser() {
		
		userService.registerUser(userName, userEmail, userCity, userCategory, shop);
		
		setUserName(null);
		setUserEmail(null);
		setUserCity(null);
		setUserCategory(null);
		setShop(null);
	}
		
	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}

	public List<Advertisement> getAds() {
		return ads;
	}

	public void setAds(List<Advertisement> ads) {
		this.ads = ads;
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

	public Distance getDistance() {
		return distance;
	}

	public void setDistance(Distance distance) {
		this.distance = distance;
	}

	public List<Distance> getDistances() {
		return distances;
	}

	public void setDistances(List<Distance> distances) {
		this.distances = distances;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPictureName() {
		return pictureName;
	}
	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}
	
	public Date getStart() {
		return start;
	}
	
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	
	public void setEnd(Date end) {
		this.end = end;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Category getUserCategory() {
		return userCategory;
	}

	public void setUserCategory(Category userCategory) {
		this.userCategory = userCategory;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public City getUserCity() {
		return userCity;
	}

	public void setUserCity(City userCity) {
		this.userCity = userCity;
	}

	public List<Shop> getShops() {
		return shops;
	}

	public void setShops(List<Shop> shops) {
		this.shops = shops;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
}
