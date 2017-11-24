package de.dpunkt.myaktion.util;

import java.util.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.dpunkt.myaktion.util.Logs.FachLog;
import de.dpunkt.myaktion.util.Logs.TecLog;

@Dependent
public class Resources {
	@Produces
<<<<<<< Upstream, based on master
	@PersistenceContext
	private EntityManager em;
	
	@Produces
	public Logger produceLog(InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName(), "messages");
=======
	@FachLog
	public Logger produceLogFach(InjectionPoint injectionPoint) {
		return Logger.getLogger("Fachlog: " + injectionPoint.getMember().getDeclaringClass().getName(), "messages");
	}
	@Produces
	@TecLog
	public Logger produceLogTec(InjectionPoint injectionPoint) {
		return Logger.getLogger("Teclog: " + injectionPoint.getMember().getDeclaringClass().getName(), "messages");
>>>>>>> e2b910a logs
	}
	
	@Produces
	@RequestScoped
	public FacesContext produceFacesContext() {
		return FacesContext.getCurrentInstance();
	}
}
