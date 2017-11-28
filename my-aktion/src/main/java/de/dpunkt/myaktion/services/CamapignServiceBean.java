package de.dpunkt.myaktion.services;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import de.dpunkt.myaktion.model.Campaign;

//@RequestScoped
@Stateless
public class CamapignServiceBean implements CampaignService {
	@Inject
	EntityManager entityManager;

	@Override
	public List<Campaign> getAllCampaigns() {
		TypedQuery<Campaign> query = entityManager.createNamedQuery(Campaign.findAll, Campaign.class);
		List<Campaign> campaigns = query.getResultList();
		campaigns.forEach(campaign -> campaign.setAmountDonatedSoFar(getAmountDonatedSoFar(campaign)));
		return campaigns;
	}
	
	public void addCampaign(Campaign campaign) {
		entityManager.persist(campaign);
	}
	
	public void deleteCampaign(Campaign campaign) {
		Campaign managedCampaign = entityManager.find(Campaign.class, campaign.getId());
		entityManager.remove(managedCampaign);
	}
	
	public void updateCampaign(Campaign campaign) {
		entityManager.merge(campaign);
	}
	
	public Double getAmountDonatedSoFar(Campaign campaign) {
		TypedQuery<Double> query = entityManager.createNamedQuery(Campaign.getAmountDonatedSoFar, Double.class);
		query.setParameter("campaign", campaign);
		Double result = query.getSingleResult();
		if(result == null) {
			result = 0d;
		}
		return result;
	}
}
