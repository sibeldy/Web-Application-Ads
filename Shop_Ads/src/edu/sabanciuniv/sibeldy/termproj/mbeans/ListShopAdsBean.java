package edu.sabanciuniv.sibeldy.termproj.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import edu.sabanciuniv.sibeldy.termproj.business.AdsService;
import edu.sabanciuniv.sibeldy.termproj.domain.Advertisement;
import edu.sabanciuniv.sibeldy.termproj.domain.Shop;

@ManagedBean
@SessionScoped
public class ListShopAdsBean implements Serializable{
	
	private Shop shop;
	
	@EJB
	private AdsService adsService;
	
	// variables
	
	private List<Advertisement> ads; 
	
	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;
	
	@PostConstruct
	public void init(){	
		shop = loginBean.getLoggedInShop();
	}
	
	public List<Advertisement> getAds() {
		init();
		this.ads = adsService.getShopAds(shop);	
		return ads;
	}

	public void setAds(List<Advertisement> ads) {
		this.ads = ads;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
}
