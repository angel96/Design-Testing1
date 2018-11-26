
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
import domain.FixUpTask;
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

	public static List<?> checkSpamWords(final Collection<? extends Actor> ls, final Collection<SpamWord> spam) {
		Collection<? extends Actor> result;
		result = new ArrayList<>();
		for (final Actor a : ls)
			if (true) {
			}
		return null; //TODO SE CAMBIARA EL METODO PARA CUMPLIR CIERTOS REQUISITOS, PERO LA IDEA SE MANTIENE PARA SER MAS EFICIENTE
	}

	public static String a(final Collection<Authority> ls) {
		String res = "";
		for (final Authority a : ls)
			if (a.getAuthority().equals(Authority.ADMIN))
				res = Authority.ADMIN;
			else if (a.getAuthority().equals(Authority.CUSTOMER))
				res = Authority.CUSTOMER;
			else if (a.getAuthority().equals(Authority.HANDY_WORKER))
				res = Authority.HANDY_WORKER;
			else if (a.getAuthority().equals(Authority.REFEREE))
				res = Authority.REFEREE;
			else if (a.getAuthority().equals(Authority.SPONSOR))
				res = Authority.SPONSOR;
		return res;
	}
}
