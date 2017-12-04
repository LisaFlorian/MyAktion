package de.dpunkt.myaktion.scheduler;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import de.dpunkt.myaktion.services.DonationService;
import de.dpunkt.myaktion.util.TransactionInterceptor;

@Singleton
public class SchedulerBean {
	@Inject
	private DonationService donationService;
	
	@Schedule(hour = "*", minute = "*/5", persistent = false)
	public void doTRansferDonations() {
		donationService.transferDonations();
	}
}
