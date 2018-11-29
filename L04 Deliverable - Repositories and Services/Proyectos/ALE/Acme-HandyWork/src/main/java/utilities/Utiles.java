
package utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;

import security.Authority;
import utilities.internal.SchemaPrinter;
import domain.Actor;
import domain.Box;
import domain.FixUpTask;
import domain.Message;
import domain.SpamWord;

public class Utiles {

	public static String generateTicker() {
		final SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");

		final Date d = new Date();

		final String formated = formato.format(d);

		final Character[] ch = {
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
		};

		String c = "";

		final Random random = new Random();

		for (int i = 0; i < 6; i++)
			c += ch[random.nextInt(ch.length)];

		return formated + c;
	}

	public static void fullTextSearch(final String s) {
		final HibernatePersistenceProvider provider = new HibernatePersistenceProvider();
		final EntityManagerFactory entityManagerFactory = provider.createEntityManagerFactory("Acme-HandyWork", null);
		final EntityManager em = entityManagerFactory.createEntityManager();
		final FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
		em.getTransaction().begin();

		final QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(FixUpTask.class).get();
		final org.apache.lucene.search.Query luceneQuery = qb.keyword().onFields("ticker", "description", "address").matching(s).createQuery();

		// From Lucene query to Javax query
		final javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, FixUpTask.class);

		final List<?> result = jpaQuery.getResultList();
		SchemaPrinter.print(result);

		em.getTransaction().commit();
		em.close();
	}

	public static void broadcastMessage(final Collection<? extends Actor> ls, final Message m) {
		Collection<Message> existingMessages;
		existingMessages = new ArrayList<>();
		Collection<Box> boxes;
		for (final Actor a : ls) {
			boxes = a.getBoxes();
			for (final Box b : boxes)
				if (b.getName().equals("entry")) {
					existingMessages = b.getMessage();
					existingMessages.add(m);
					b.setMessage(existingMessages);
				}
		}
	}

	public static void sendIndividualMessage(final Actor sender, final Actor recipient, final Message m) {
		Collection<Message> existing;
		existing = new ArrayList<>();
		m.setSender(sender);
		m.setReceiver(recipient);
		for (final Box b : recipient.getBoxes())
			if (b.getName().equals("entry")) {
				existing = b.getMessage();
				existing.add(m);
				b.setMessage(existing);
			}
	}
	public static Boolean checkCollectionsSpam(final List<SpamWord> spam, final String[]... fields) {
		Boolean res = false;
		for (final String[] s : fields)
			//			for (int i = 0; i < spam.size(); i++)
			for (final SpamWord sw : spam)
				if (sw.getWord().equals(s.toString())) {
					res = true;
					break;
				}
		return res;
	}
	public static Boolean checkSpamStrings(final List<SpamWord> spam, final String s) {
		Boolean res = false;
		for (final SpamWord sw : spam)
			if (sw.getWord().equals(s)) {
				res = true;
				break;
			}
		return res;
	}

	public static Boolean findAuthority(final Collection<Authority> comp, final String a) {
		Boolean res = false;
		if (comp.size() > 1) {
			final Authority aut = new Authority();
			aut.setAuthority(a);
			res = comp.contains(aut);
		} else
			for (final Authority authority : comp)
				if (authority.toString().equals(a))
					res = true;

		return res;
	}
}
