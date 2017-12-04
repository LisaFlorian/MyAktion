package de.dpunkt.myaktion.data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import de.dpunkt.myaktion.model.Account;
import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.model.Donation;
import de.dpunkt.myaktion.model.Donation.Status;
import de.dpunkt.myaktion.services.CampaignService;
import de.dpunkt.myaktion.util.CampaignEvent;
import de.dpunkt.myaktion.util.Events.Added;
import de.dpunkt.myaktion.util.Events.Deleted;
import de.dpunkt.myaktion.util.Events.Updated;

@RequestScoped
public class CampaignListProducer{

	private List<Campaign> campaigns;
	@Inject
	private CampaignService campaignService;
	@Inject
	private Logger logger;
	
	@PostConstruct
	public void init() {
		campaigns = campaignService.getAllCampaigns();
	}
	@Produces
	@Named
	public List<Campaign> getCampaigns() {
		return campaigns;
	}
	public void onCampaignAdded(@Observes @Added CampaignEvent campaignEvent) {
		campaignService.addCampaign(campaignEvent.getCampaign());
		init();
	}
	public void onCampaignDeleted(@Observes @Deleted CampaignEvent campaignEvent) {
		campaignService.deleteCampaign(campaignEvent.getCampaign());
		init();
	}
	public void onCampaignUpdated(@Observes @Updated CampaignEvent campaignEvent) {
		campaignService.updateCampaign(campaignEvent.getCampaign());
		init();
	}
	
	
	
}
