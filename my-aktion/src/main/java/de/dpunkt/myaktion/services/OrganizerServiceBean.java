package de.dpunkt.myaktion.services;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import de.dpunkt.myaktion.model.Organizer;

@RequestScoped
public class OrganizerServiceBean implements OrganizerService {
	@Inject
	EntityManager entityManager;

	@Override
	@PermitAll
	public void addOrganizer(Organizer o) {
		Organizer managedOrganizer = entityManager.find(Organizer.class, o.getEmail());
		if(managedOrganizer != null) {
			throw new RuntimeException("Organizer mit dieser Email-Adresse bereits vorhanden");
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] encryptedPassword = messageDigest.digest(o.getPassword().getBytes(StandardCharsets.UTF_8));
			StringBuffer hexString = new StringBuffer();
	        for (int i = 0; i < encryptedPassword.length; i++) {
	            String hex = Integer.toHexString(0xff & encryptedPassword[i]);
	            if(hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }
	        o.setPassword(hexString.toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		entityManager.persist(o);
	}

}
