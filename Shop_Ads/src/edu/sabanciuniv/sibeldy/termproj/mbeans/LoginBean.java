package edu.sabanciuniv.sibeldy.termproj.mbeans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.sabanciuniv.sibeldy.termproj.business.AdsService;
import edu.sabanciuniv.sibeldy.termproj.domain.Shop;

@ManagedBean
@SessionScoped
public class LoginBean {
	
	private String email;
	private String password;
	
	@EJB
	private AdsService adsService;
	
	private Shop loggedInShop;
	
	public String login() {
		
		Shop shop = adsService.login(email, password);
		
		if (shop == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Wrong credentials!!!"));
			return "login";
		} else {
			this.loggedInShop = shop;
			return "shop/listShopAds?faces-redirect=true"; 
		}	
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

	public Shop getLoggedInShop() {
		return loggedInShop;
	}

	public void setLoggedInShop(Shop loggedInShop) {
		this.loggedInShop = loggedInShop;
	}

	
}
