package edu.sabanciuniv.sibeldy.termproj.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import edu.sabanciuniv.sibeldy.termproj.domain.Advertisement;
import edu.sabanciuniv.sibeldy.termproj.domain.Category;
import edu.sabanciuniv.sibeldy.termproj.domain.City;
import edu.sabanciuniv.sibeldy.termproj.domain.Distance;
import edu.sabanciuniv.sibeldy.termproj.domain.Shop;
import edu.sabanciuniv.sibeldy.termproj.domain.User;

@Stateless
public class UserService implements Serializable {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Category> listCategories() {
		return entityManager.createQuery("select category from Category category", Category.class).getResultList();
	}

	public List<Distance> listDistances() {
		return entityManager.createQuery("select distance from Distance distance", Distance.class).getResultList();
	}

	public List<Advertisement> listAll(Date today) {
		// get all active advertisements - Cat ALL Dist ALL
		return entityManager.createQuery("select ads from Advertisement ads where ads.start<=:date and ads.end>=:date",
				Advertisement.class).setParameter("date", today).getResultList();
	}

	public List<Advertisement> selectByCategory(Category cat, Date today) {
		// get by category, distance not selected - Cat selected Dist ALL
		return entityManager.createQuery(
				"select ads from Advertisement ads where ads.start<=:date and ads.end>=:date and ads.shop.category=:cat",
				Advertisement.class).setParameter("date", today).setParameter("cat", cat).getResultList();
	}

	public List<Advertisement> selectByDistance(Category cat, Distance dist, double lat, double lng, Date today) {

		List<Shop> shops = entityManager.createQuery("select shop from Shop shop ", Shop.class).getResultList();

		List<Shop> selectByDistance = new ArrayList<>();

		for (Shop shop : shops) {

			double lat_shop = shop.getLatitude();
			double lng_shop = shop.getLongitude();

			double distance_calc = calculateDistance(lat, lng, lat_shop, lng_shop);

			if (distance_calc <= dist.getDistance()) {
				selectByDistance.add(shop);
				System.out.println(shop.getShopName() + " _Distance: " + distance_calc);
			}
		}

		if (cat == null) {

			return listAds(selectByDistance, today);
			
		} else {

			return listAdsCat(selectByDistance, today, cat);
		}

	}

	private double calculateDistance(double lat, double lng, double lat_shop, double lng_shop) {

		double radlat1 = Math.toRadians(lat);
		double radlat2 = Math.toRadians(lat_shop);
		double radtheta = Math.toRadians(lng - lng_shop);

		double distance = Math.sin(radlat1) * Math.sin(radlat2)
				+ Math.cos(radlat1) * Math.cos(radlat2) * Math.cos(radtheta);

		distance = Math.acos(distance);
		distance = Math.toDegrees(distance);
		distance = distance * 60 * 1.1515 * 1.609344 * 1000;

		return distance;
	}
	

	private List<Advertisement> listAdsCat(List<Shop> shops, Date today, Category cat) {

		List<Advertisement> ads = new ArrayList<>();

		for (Shop shop : shops) {

			List<Advertisement> shopAds = entityManager.createQuery(
					"select ads from Advertisement ads where ads.shop.id=:shopId and ads.start<=:date and ads.end>=:date and ads.shop.category=:cat",
					Advertisement.class).setParameter("shopId", shop.getId()).setParameter("date", today).setParameter("cat", cat)
					.getResultList();

			ads.addAll(shopAds);

		}
		return ads;
	}
	

	public List<Advertisement> listAds(List<Shop> shops, Date today) {

		List<Advertisement> ads = new ArrayList<>();

		for (Shop shop : shops) {

			List<Advertisement> shopAds = entityManager.createQuery(
					"select ads from Advertisement ads where ads.shop.id=:shopId and ads.start<=:date and ads.end>=:date",
					Advertisement.class).setParameter("shopId", shop.getId()).setParameter("date", today)
					.getResultList();

			ads.addAll(shopAds);

		}
		return ads;
	}

	public List<Shop> listShops() {
		return entityManager.createQuery("select shopName from Shop shopName", Shop.class).getResultList();
	}

	public List<City> listCities() {
		return entityManager.createQuery("select city from City city", City.class).getResultList();
	}

	public void registerUser(String userName, String userEmail, City userCity, Category userCategory, Shop shop) {
		 User newUser = new User(userName, userEmail, userCity, userCategory, shop);
			
		 entityManager.persist(newUser);
		
	}

}
