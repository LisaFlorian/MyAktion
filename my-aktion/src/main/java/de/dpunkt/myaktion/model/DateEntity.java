package de.dpunkt.myaktion.model;

import java.util.Date;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class DateEntity {
	private Date createdAt;

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
