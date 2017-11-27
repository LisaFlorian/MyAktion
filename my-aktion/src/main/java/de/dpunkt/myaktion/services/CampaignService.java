package de.dpunkt.myaktion.services;

import java.util.List;

import de.dpunkt.myaktion.model.Campaign;

public interface CampaignService {
	List<Campaign> getAllCampaigns();
	void addCampaign(Campaign campaign);
	void deleteCampaign(Campaign campaign);
	void updateCampaign(Campaign campaign);
}
