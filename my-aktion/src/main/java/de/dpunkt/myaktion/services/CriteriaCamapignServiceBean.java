package de.dpunkt.myaktion.services;

import java.beans.Expression;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.model.Donation;

//@RequestScoped
@Stateless
@Alternative
public class CriteriaCamapignServiceBean implements CampaignService {
	@Inject
	EntityManager entityManager;

	@Override
	public List<Campaign> getAllCampaigns() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Campaign> cq = cb.createQuery(Campaign.class);
		Root<Campaign> campaign = cq.from(Campaign.class);
		cq.orderBy(cb.asc(campaign.get("name")));
		cq.select(campaign);
		TypedQuery<Campaign> query = entityManager.createQuery(cq);
		List<Campaign> campaigns = query.getResultList();
		campaigns.forEach(campaign2 -> campaign2.setAmountDonatedSoFar(getAmountDonatedSoFar(campaign2)));
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
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Double> cq = cb.createQuery(Double.class);
		Root<Donation> donation = cq.from(Donation.class);
		cq.select(cb.sumAsDouble(donation.get("amount")));
		ParameterExpression<Campaign> p = cb.parameter(Campaign.class);
		cq.where(cb.equal(donation.get("campaign"), p));
		TypedQuery<Double> query = entityManager.createQuery(cq);
		query.setParameter(p, campaign);
		Double result = query.getSingleResult();
		if(result == null) {
			result = 0d;
		}
		return result;
	}
}
