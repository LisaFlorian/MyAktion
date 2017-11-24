package de.dpunkt.myaktion.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import javax.inject.Qualifier;

public class Events {
	@Qualifier
	@Target({ElementType.FIELD,ElementType.PARAMETER})
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Added {
	}
	@Qualifier
	@Target({ElementType.FIELD,ElementType.PARAMETER})
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Deleted {
	}
}
