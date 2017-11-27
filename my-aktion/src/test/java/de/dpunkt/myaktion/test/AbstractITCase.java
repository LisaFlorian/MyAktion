package de.dpunkt.myaktion.test;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public abstract class AbstractITCase {
	@Deployment(testable=false)
	public static WebArchive createDeployment() {
		WebArchive archive = ShrinkWrap.create(ZipImporter.class, "test.war").importFrom(new File("target/my-aktion.war")).as(WebArchive.class);
		archive.delete("/WEB-INF/classes/META-INF/persistence.xml");
		//archive.addAsResource("META-INF/persistence-test.xml", "META-INF/persistence.xml");
		return archive;
	}
}
