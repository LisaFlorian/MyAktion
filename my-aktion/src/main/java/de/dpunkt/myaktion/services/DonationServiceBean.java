package de.dpunkt.myaktion.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.model.Donation;
import de.dpunkt.myaktion.model.Donation.Status;

@Stateless
public class DonationServiceBean implements DonationService {
	@Inject
	EntityManager entityManager;
	@Inject
	private Logger logger;
	
	@Override
	@RolesAllowed("Organizer")
	public List<Donation> getDonationList(Long campaignId) {
		Campaign managedCampaign = entityManager.find(Campaign.class, campaignId);
		List<Donation> donations = managedCampaign.getDonations();
		donations.size();
		return donations;
	}

	@Override
	@PermitAll
	public void addDonation(Long campaignId, Donation donation) {
		Campaign managedCampaign = entityManager.find(Campaign.class, campaignId);
		if(donation.getAmount() < managedCampaign.getDonationMinimum())
		{
			throw new RuntimeException("mindestbetrag ist "+managedCampaign.getDonationMinimum()+" euro");
		}
		donation.setCampaign(managedCampaign);
		entityManager.persist(donation);
	}
	
	@Override
	@PermitAll
	public void transferDonations() {
		logger.log(Level.INFO, "log.transferDonation.start");
		TypedQuery<Donation> query = entityManager.createNamedQuery(Donation.findByStatus, Donation.class);
		query.setParameter("status", Status.IN_PROCESS);
		List<Donation> donations = query.getResultList();
		donations.forEach(donation -> donation.setStatus(Status.TRANSFERRED));
		logger.log(Level.INFO, "log.transferDonation.done", new Object[] {donations.size()});
	}

}
