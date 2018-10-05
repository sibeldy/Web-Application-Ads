package edu.sabanciuniv.sibeldy.termproj.mbeans;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import org.omnifaces.util.Utils;

import edu.sabanciuniv.sibeldy.termproj.business.AdsService;

@ManagedBean
@SessionScoped
public class AdsBean implements Serializable {
	
	@EJB
	private AdsService adsService;
	
	private String productName;
	private String description;
	private String pictureName;
	private Date start;
	private Date end;
	private Part file;
	private byte[] content;
	private String pathPicture ="";
	
	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;
	
	public void read() throws IOException {
        content = Utils.toByteArray(file.getInputStream());
        upload();
    }
	
	public void  upload() {
		
		if(file !=null ) {
		
		  pathPicture = file.getSubmittedFileName();
		}
		
		if(content!=null) {
		try (FileOutputStream fos = new FileOutputStream("../Users/sibelyilmaz/Documents/Sibel/Folders/0/project_photos/" +  file.getSubmittedFileName())) {
			   fos.write(content);
			   //fos.close(); There is no more need for this line since you had created the instance of "fos" inside the try. And this will automatically close the OutputStream
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
	
	public String saveAd() {
		
		if (productName ==null || description == null || start ==null || end == null) {
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please fill all of the form!"));
			return null;
			
		}
		else if(start.compareTo(end) > 0 ) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("End date can't be earlier than start!"));
			return null;
		}
		else if(start.compareTo(new Date()) < 0 ) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Start date can't be earlier than today!"));
			return null;
		}
		else {
		
		adsService.saveAd(getProductName(), getDescription(), getStart(), getEnd(), getPathPicture() , loginBean.getLoggedInShop());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Saved successfully!"));
		setProductName(null);
		setDescription(null);
		setEnd(null);
		setStart(null);
		setFile(null);
		setContent(null);
		return "shop/listAds";	
		}	
	}
	
	public byte[] getContent() {
		return content;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
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

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public String getPathPicture() {
		return pathPicture;
	}

	public void setPathPicture(String pathPicture) {
		this.pathPicture = pathPicture;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}	
	
}
