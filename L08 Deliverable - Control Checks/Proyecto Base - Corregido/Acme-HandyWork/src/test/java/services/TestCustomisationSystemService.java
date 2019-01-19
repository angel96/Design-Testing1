
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import repositories.MesageRepository;
import utilities.AbstractTest;
import utilities.Utiles;
import domain.Actor;
import domain.Mesage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestCustomisationSystemService extends AbstractTest {

	@Autowired
	private CustomisationSystemService	serviceCustomisation;

	@Autowired
	private MesageRepository			repositoryMesage;


	@Test
	public void checkSpamWords() {

		final Collection<Mesage> mesage = this.repositoryMesage.findAll();
		Utiles.spamWords = this.serviceCustomisation.findUnique().getSpamWords();
		for (final Mesage m : mesage) {
			final Actor a = m.getSender();
			System.out.println(m.getId());
			System.out.println(m.getSender().getId());
			System.out.println(m.getSubject());
			System.out.println(m.getBody());
			System.out.println("Subject: " + Utiles.spamWord(Utiles.limpiaString(m.getSubject())));
			System.out.println("Body: " + Utiles.spamWord(Utiles.limpiaString(m.getBody())));
			a.setSuspicious(Utiles.spamWord(Utiles.limpiaString(m.getSubject())) || Utiles.spamWord(Utiles.limpiaString(m.getBody())));
			System.out.println("Suspicious: " + a.isSuspicious());
			System.out.println("===================================================================\n");
		}

	}
}
