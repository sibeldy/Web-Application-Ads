package edu.sabanciuniv.sibeldy.termproj.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import edu.sabanciuniv.sibeldy.termproj.business.AdsService;
import edu.sabanciuniv.sibeldy.termproj.domain.Category;
import edu.sabanciuniv.sibeldy.termproj.domain.City;
import edu.sabanciuniv.sibeldy.termproj.domain.District;
import edu.sabanciuniv.sibeldy.termproj.domain.Shop;

@ManagedBean
@ViewScoped
public class RegisterShopBean implements Serializable{

	private String shopName;
	private String email;
	private String password;
	private String repassword;
	private String address;
	private String cadde;
	private String sokak;
	private String no;
	private String telephone;
	private Category category;
	private double longitude;
	private double latitude;
	private City city;
	private District district;
	private List<Category> categories;
	private List<City> cities;
	private List<District> districts;

	@EJB
	private AdsService adsService;
	
	@PostConstruct
	private void init() {
		categories = adsService.listCategories();
		cities = adsService.listCities();
	}
	
	public void selectedCity(AjaxBehaviorEvent event) {
		
		this.districts = adsService.listDistricts(city.getId());
	}

	public String addShop() {
	
		if (adsService.isEmailRegistered(email)==true){
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("This email is already registered!"));
			return null;
		
		}
		
	    else if (shopName ==null || email==null || password==null || repassword==null ||
			cadde==null || sokak ==null || no==null || telephone==null || category==null ||
			city==null || district==null || longitude==0.0 && latitude==0.0) {
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Required Info missing!"));
			return null;
		}
	
		else {
		
		this.address = getCadde() + " cd. " + getSokak() + " sk. " + "No: " + getNo();
		
		adsService.addShop(shopName, email, password, address, telephone, category, longitude, latitude, city,
				district);

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registration is successful!"));
		return "login";
		}
	}
	
	public void makeAddress() {
		address = getCadde() + " cd." + getSokak() + " sk." + "No: " + getNo();
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

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getAddress() {
		return address;
	}


	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public AdsService getAdsService() {
		return adsService;
	}

	public void setAdsService(AdsService adsService) {
		this.adsService = adsService;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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

	public List<Category> getCategories() {
		return categories;
	}

	public List<City> getCities() {
		return cities;
	}

	public List<District> getDistricts() {
		return districts;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}

	public String getCadde() {
		return cadde;
	}

	public void setCadde(String cadde) {
		this.cadde = cadde;
	}

	public String getSokak() {
		return sokak;
	}

	public void setSokak(String sokak) {
		this.sokak = sokak;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
}
