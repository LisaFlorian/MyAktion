package de.dpunkt.myaktion.util;

import java.util.Calendar;

import javax.persistence.PrePersist;

import de.dpunkt.myaktion.model.DateEntity;

public class DateCreatedEntityListener {
	@PrePersist
	void onPrePersist(Object entity) {
		if(entity instanceof DateEntity)
		{
			((DateEntity) entity).setCreatedAt(Calendar.getInstance().getTime());
		}
	}
}
