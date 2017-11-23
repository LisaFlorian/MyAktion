package de.dpunkt.myaktion.controller;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import de.dpunkt.myaktion.model.Donation;
import de.dpunkt.myaktion.model.FormConfig;

@SessionScoped
@Named
public class DonateMoneyController implements Serializable {
	private static final long serialVersionUID = 5493038842003809106L;
	
	private FormConfig formConfig;
	private Long campaignId;
	private Donation donation;
	
	public DonateMoneyController() {
		this.donation = new Donation();
		this.formConfig = new FormConfig();
	}
	
	public Long getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}
	public Donation getDonation() {
		return donation;
	}
	public void setDonation(Donation donation) {
		this.donation = donation;
	}
	public FormConfig getFormConfig() {
		return formConfig;
	}
	public void setFormConfig(FormConfig formConfig) {
		this.formConfig = formConfig;
	}

	public String doDonation() {
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		final ResourceBundle resourceBundle = facesContext.getApplication().getResourceBundle(facesContext, "msg");
		final String msg = resourceBundle.getString("donateMoney.thank_you");
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
		this.donation = new Donation();
		return Pages.DONATE_MONEY;
	}
}
