package de.dpunkt.myaktion.test;

import org.jboss.arquillian.graphene.Graphene;

import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.test.pages.EditCampaignPage;
import de.dpunkt.myaktion.test.pages.ListCampaignsPage;

public class SetupDatabase {
	public static void addCampaign(final Campaign campaign) {
		final EditCampaignPage editCampaignPage = Graphene.goTo(EditCampaignPage.class);
		final ListCampaignsPage listCampaignsPage = Graphene.goTo(ListCampaignsPage.class);
		listCampaignsPage.doAddCampaign();
		editCampaignPage.assertOnPage();
		editCampaignPage.setCampaign(campaign);
		editCampaignPage.doSave();
	}
}
