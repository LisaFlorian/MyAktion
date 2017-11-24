package de.dpunkt.myaktion.services;

import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import de.dpunkt.myaktion.model.Campaign;

@RequestScoped
public class CamapignServiceBean implements CampaignService {

	@Override
	public List<Campaign> getAllCampaigns() {
		return new LinkedList<Campaign>();
	}

}
