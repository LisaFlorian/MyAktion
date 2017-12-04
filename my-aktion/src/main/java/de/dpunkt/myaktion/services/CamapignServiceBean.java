package de.dpunkt.myaktion.services;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.model.Organizer;
import de.dpunkt.myaktion.util.TransactionInterceptor;
import de.dpunkt.myaktion.util.PerformanceAuditor;

@RequestScoped
@RolesAllowed("Organizer")
@Interceptors({TransactionInterceptor.class, PerformanceAuditor.class})
public class CamapignServiceBean implements CampaignService {
	@Inject
	EntityManager entityManager;
	@Inject
	private Principal principal;

	@Override
	public List<Campaign> getAllCampaigns() {
		TypedQuery<Campaign> query = entityManager.createNamedQuery(Campaign.findByOrganizer, Campaign.class);
		query.setParameter("organizer", getLoggedinOrganizer());
		List<Campaign> campaigns = query.getResultList();
		campaigns.forEach(campaign -> campaign.setAmountDonatedSoFar(getAmountDonatedSoFar(campaign)));
		return campaigns;
	}
	
	public void addCampaign(Campaign campaign) {
		Organizer organizer = getLoggedinOrganizer();
		campaign.setOrganizer(organizer);
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
	
	private Organizer getLoggedinOrganizer() {
		String organizerEmail = principal.getName();
		System.out.println("organizerEmail:" + organizerEmail);
		Organizer organizer = entityManager.createNamedQuery(Organizer.findByEmail, Organizer.class).setParameter("email", organizerEmail).getSingleResult();
		return organizer;
	}
}
