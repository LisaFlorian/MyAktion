package de.dpunkt.myaktion.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


import de.dpunkt.myaktion.data.CampaignListProducer;
import de.dpunkt.myaktion.data.CampaignProducer;

import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.util.CampaignEvent;
import de.dpunkt.myaktion.util.Events.Added;

@ViewScoped
@Named
public class EditCampaignController implements Serializable {
	private static final long serialVersionUID = 2815796004558360299L;
	
	@Inject
	private CampaignProducer campaignProducer;
	@Inject
	@Added
	private Event<CampaignEvent> campaignAddEvent;
	
	public String doSave() {
		if(campaignProducer.isAddMode()) {
			campaignAddEvent.fire(new CampaignEvent(campaignProducer.getSelectedCampaign()));
		}
		return Pages.LIST_CAMPAIGNS;
	}
	
	public String doCancel() {
		return Pages.LIST_CAMPAIGNS;
	}
}
