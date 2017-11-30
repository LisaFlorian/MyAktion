package de.dpunkt.myaktion.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.dpunkt.myaktion.model.Organizer;
import de.dpunkt.myaktion.services.OrganizerService;

@ViewScoped
@Named
public class EditOrganizerController implements Serializable {
	private static final long serialVersionUID = 2815796004559L;

	private Organizer organizer;
	@Inject
	private OrganizerService organizerService;
	
	@PostConstruct
	public void init() {
		this.organizer = new Organizer();
	}
	
	public String goToAddOrganizer() {
		return Pages.ADD_ORGANIZER;
	}
	
	public String doCreate() {
		organizerService.addOrganizer(organizer);
		return Pages.LOGIN;
	}

	public Organizer getOrganizer() {
		return organizer;
	}

	public void setOrganizer(Organizer organizer) {
		this.organizer = organizer;
	}
}
