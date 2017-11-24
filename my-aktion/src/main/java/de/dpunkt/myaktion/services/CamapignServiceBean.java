package de.dpunkt.myaktion.services;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.model.Organizer;
import de.dpunkt.myaktion.util.Logs.TecLog;

//@RequestScoped
@Stateless
@RolesAllowed("Organizer")
public class CamapignServiceBean implements CampaignService {
	@Inject
	EntityManager entityManager;
	@Resource
	private SessionContext sessionContext;

	@Inject
	@TecLog
	private Logger logger;
	
	@Override
	public List<Campaign> getAllCampaigns() {
		logger.log(Level.INFO, "log.get_all_campaigns");
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
		String organizerEmail = sessionContext.getCallerPrincipal().getName();
		Organizer organizer = entityManager.createNamedQuery(Organizer.findByEmail, Organizer.class).setParameter("email", organizerEmail).getSingleResult();
		return organizer;
	}
}
