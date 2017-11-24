package de.dpunkt.myaktion.test;

import org.jboss.arquillian.drone.api.annotation.Default;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.context.GrapheneContext;
import org.openqa.selenium.WebDriver;

import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.model.Donation;
import de.dpunkt.myaktion.test.pages.DonateMoneyPage;
import de.dpunkt.myaktion.test.pages.EditCampaignPage;
import de.dpunkt.myaktion.test.pages.EditDonationFormPage;
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
	
	public static void addDonation(final Donation donation) {
		final DonateMoneyPage donateMoneyPage = Graphene.goTo(DonateMoneyPage.class);
		final EditDonationFormPage editDonationFormPage = Graphene.goTo(EditDonationFormPage.class);
		final ListCampaignsPage listCampaignsPage = Graphene.goTo(ListCampaignsPage.class);
		listCampaignsPage.clickCampaignUrl();
		editDonationFormPage.assertOnPage();
		WebDriver browser = GrapheneContext.getContextFor(Default.class).getWebDriver();
		browser.navigate().to(editDonationFormPage.getFormURL());
		donateMoneyPage.assertOnPage();
		donateMoneyPage.setDonation(donation);
		donateMoneyPage.doDonation();
		donateMoneyPage.assertThankYou();
	}
}
