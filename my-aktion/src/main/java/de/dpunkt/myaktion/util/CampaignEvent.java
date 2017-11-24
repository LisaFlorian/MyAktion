package de.dpunkt.myaktion.util;

import de.dpunkt.myaktion.model.Campaign;

public class CampaignEvent {
	private Campaign campaign;
	
	

	public CampaignEvent(Campaign campaign) {
		super();
		this.campaign = campaign;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}
	
	
}
