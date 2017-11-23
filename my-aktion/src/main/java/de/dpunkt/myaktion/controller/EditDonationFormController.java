package de.dpunkt.myaktion.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import de.dpunkt.myaktion.data.CampaignProducer;
import de.dpunkt.myaktion.model.FormConfig;

@SessionScoped
@Named
public class EditDonationFormController implements Serializable {
	private static final long serialVersionUID = -4210085664588144340L;
	
	private FormConfig formConfig;
	
	@Inject
	private CampaignProducer campaignProducer;
	
	public EditDonationFormController() {
		super();
		formConfig = new FormConfig();
	}

	public String doOk() {
		return Pages.LIST_CAMPAIGNS;
	}
	
	private String getAppURL() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		return scheme + "://" + serverName + ":" + serverPort + contextPath;
	}
	
	public String getUrl() {
		return getAppURL() + "/" + Pages.DONATE_MONEY + ".jsf?bgColor=" + formConfig.getBgColor() + "&textColor=" + formConfig.getTextColor() + 
				"&title=" + formConfig.getTitle() + "&campaignId=" + campaignProducer.getSelectedCampaign().getId(); 
	}

	public FormConfig getFormConfig() {
		return formConfig;
	}

	public void setFormConfig(FormConfig formConfig) {
		this.formConfig = formConfig;
	}
}
