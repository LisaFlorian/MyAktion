package de.dpunkt.myaktion.util;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.transaction.UserTransaction;

public class PerformanceAuditor {
	@Inject
	private Logger logger;
	
	@AroundInvoke
	public Object logMethodTime(InvocationContext ctx) throws Exception {
		long time = System.currentTimeMillis();
		Object ret = ctx.proceed();
		long duration = System.currentTimeMillis() - time;
		logger.info(String.format( ctx.getMethod() + " hat %d Sekunden ("+duration+" Millisekunden) benötigt.", 
				TimeUnit.MILLISECONDS.toSeconds(duration)));
		return ret;
	}
}
