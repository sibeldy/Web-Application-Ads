package edu.sabanciuniv.sibeldy.termproj.business;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.sabanciuniv.sibeldy.termproj.domain.Advertisement;
import edu.sabanciuniv.sibeldy.termproj.domain.Category;
import edu.sabanciuniv.sibeldy.termproj.domain.City;
import edu.sabanciuniv.sibeldy.termproj.domain.District;
import edu.sabanciuniv.sibeldy.termproj.domain.Shop;
import edu.sabanciuniv.sibeldy.termproj.domain.User;

@Stateless
public class AdsService {

	@PersistenceContext
	private EntityManager entityManager;
	
	public boolean isEmailRegistered(String email) {
		
		List<Shop> shops= entityManager.createQuery("select shop from Shop shop where shop.email=:email", Shop.class)
				.setParameter("email", email).getResultList();
		
		if (shops.size() == 0) {
			return false;
		}
		
		return true;		
	}

	public List<Category> listCategories() {
		return entityManager.createQuery("select category from Category category", Category.class).getResultList();
	}

	public List<City> listCities() {
		return entityManager.createQuery("select city from City city", City.class).getResultList();
	}

	public List<District> listDistricts(int cityId) {

		return entityManager
				.createQuery("select district from District district where district.city.id=:sid", District.class)
				.setParameter("sid", cityId).getResultList();
	}

	public void addShop(String shopName, String email, String password, String address, String telephone,
			Category category, double longitude, double latitude, City city, District district) {

		Shop newShop = new Shop(shopName, email, password, address, telephone, category, longitude, latitude, city,
				district);

		/*System.out.println((shopName + " " + email + " " + password + " " + address + " " + telephone + " " + category
				+ " " + longitude + " " + latitude + " " + city + " " + district).toString());*/

		entityManager.persist(newShop);
	}

	public Shop login(String email, String password) {

		List<Shop> shops = entityManager
				.createQuery("select s from Shop s where s.email=:email and s.password=:pswd", Shop.class)
				.setParameter("email", email).setParameter("pswd", password).getResultList();

		if (shops.size() == 1) {
			return shops.get(0);
		}

		return null;
	}

	public void saveAd(String productName, String description, Date start, Date end, String submittedFileName, Shop shop) {
		 Advertisement newAd = new Advertisement(productName, description, start, end, submittedFileName, shop);
		
		 entityManager.persist(newAd);
		 
		 List<User> mailList = entityManager.createQuery("select user from User user where user.shop=:shop or user.city=:city or user.category=:category", User.class)
					.setParameter("shop", shop).setParameter("city", shop.getCity()).setParameter("category", shop.getCategory()).getResultList();
		 
		 String content = "----------------------------------/n" +
			  	  "Product Name: " + newAd.getProductName() + "/n" +
			  	  "Description: " + newAd.getDescription() + "/n" +
			  	  "Shop: " + newAd.getShop().getShopName() + "/n" +
			  	  "Start: " + newAd.getStart() + "/n" +
			  	  "End: " + newAd.getEnd()+ "/n";
		 
		 String title = "New Advertisements";
		 
		 for (User user : mailList) {
		 
		 Email.sendMail(user.getEmail(), title, content);
		 }
	}

	public List<Advertisement> getShopAds(Shop shop) {

		return entityManager.createQuery("select ad from Advertisement ad where ad.shop=:shop", Advertisement.class)
				.setParameter("shop", shop).getResultList();
		
	}

}
