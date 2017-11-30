package de.dpunkt.myaktion.test;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import de.dpunkt.myaktion.test.pages.ListCampaignsPage;

@RunWith(Arquillian.class)
public class ListDonationsITCase extends AbstractITCase {
	@Drone
	private WebDriver browser;
	
	@Before
	public void setupDatabase() {
		login();
		SetupDatabase.addCampaign(DataFactory.createTestCampaign());
		SetupDatabase.addDonation(DataFactory.createDonation());
		SetupDatabase.addDonation(DataFactory.createDonation());
	}
	
	@Test
	public void testAmountDonatedSoFar(@InitialPage ListCampaignsPage listCampaignsPage) {
		//listCampaignsPage.assertAmountDonatedSoFar((Double) 40d);
	}
}
