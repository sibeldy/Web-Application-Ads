package edu.sabanciuniv.sibeldy.termproj.mbeans;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.Marker;

import edu.sabanciuniv.sibeldy.termproj.business.UserService;
import edu.sabanciuniv.sibeldy.termproj.domain.Advertisement;
import edu.sabanciuniv.sibeldy.termproj.domain.Shop;


@ManagedBean
@ViewScoped
public class ShopsBean implements Serializable{
  
    @EJB
	private UserService userService;

	private DefaultMapModel mapModel = new DefaultMapModel();
	private Marker selectedMarker = new Marker(new LatLng(0, 0),"");
	
	private Date today;
	private  List<Advertisement> ads;
	
	@PostConstruct
	public void init()
	{
		today = new Date();
	    this.ads = userService.listAll(today);
		
		for(Advertisement ad: this.ads)
		{
			Shop shop = ad.getShop();
			LatLng latLng = new LatLng(shop.getLatitude(), shop.getLongitude());
			this.mapModel.addOverlay(new Marker(latLng, shop.getShopName()));
		}
	}
	
	 public void onMarkerSelect(OverlaySelectEvent event) {
	        this.selectedMarker = (Marker) event.getOverlay();
	    }

	public DefaultMapModel getMapModel() {
		return mapModel;
	}

	public void setMapModel(DefaultMapModel mapModel) {
		this.mapModel = mapModel;
	}

	public Marker getSelectedMarker() {
		return selectedMarker;
	}

	public void setSelectedMarker(Marker selectedMarker) {
		this.selectedMarker = selectedMarker;
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
 
}
