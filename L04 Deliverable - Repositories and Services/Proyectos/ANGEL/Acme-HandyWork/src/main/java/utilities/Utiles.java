
package utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;

import security.Authority;
import security.UserAccount;
import utilities.internal.SchemaPrinter;
import domain.Box;
import domain.CreditCard;
import domain.Endorsable;
import domain.Endorsement;
import domain.FixUpTask;
import domain.Message;
import domain.Profile;
import domain.Section;
import domain.Sponsor;
import domain.Sponsorship;
import domain.Tutorial;
import domain.Warranty;
import domain.Word;

public class Utiles {

	public static String generateTicker() {
		SimpleDateFormat formato;
		formato = new SimpleDateFormat("yyyyMMdd");

		Date d;
		d = new Date();

		String formated;

		formated = formato.format(d);

		final Character[] ch = {
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
		};

		String c = "";

		Random random;

		random = new Random();

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

	public static Sponsor createSponsor() {
		Sponsor w;

		w = new Sponsor();

		w.setName("");
		w.setMiddleName("");
		w.setSurname("");
		w.setEmail("");
		w.setAdress("");
		w.setBan(false);
		w.setPhoto("");
		w.setMessage(new ArrayList<Message>());
		w.setSponsorship(new ArrayList<Sponsorship>());
		w.setProfiles(new ArrayList<Profile>());
		w.setBoxes(new ArrayList<Box>());
		UserAccount user;
		user = new UserAccount();
		user.setUsername("");
		user.setPassword("");

		Authority authority;
		authority = new Authority();

		authority.setAuthority(Authority.SPONSOR);

		user.addAuthority(authority);

		w.setAccount(user);

		return w;
	}
	public static Tutorial createTutorial() {
		Tutorial t;
		t = new Tutorial();
		t.setTitle("");
		t.setSummary("");
		t.setSection(new ArrayList<Section>());
		t.setPicture(new ArrayList<String>());
		t.setLastUpdate(new Date());
		t.setSponsorship(new ArrayList<Sponsorship>());
		return t;
	}
	public static Section createSection() {

		Section s;

		s = new Section();

		s.setTitle("");
		s.setText("");
		s.setNumber(1);
		s.setPicture(new ArrayList<String>());

		return s;
	}
	public static Warranty createWarranty() {

		Warranty w;
		w = new Warranty();

		w.setTitle("");
		w.setLaws("");
		w.setTerms("");
		w.setDraftMode(true);

		return w;
	}
	public static Word createWord() {
		Word w;
		w = new Word();
		w.setWord("");
		w.setIsGood(true);
		return w;
	}
	//Authenticated as Customer or HandyWorker
	public static Endorsement create(final Endorsable send, final Endorsable receive) {
		Endorsement e;
		e = new Endorsement();
		e.setMoment(new Date());
		e.setUserReceived(receive);
		e.setUserSended(send);
		e.setComments(new ArrayList<String>());
		return e;
	}
	public static Sponsorship create() {
		Sponsorship result;
		result = new Sponsorship();

		result.setUrlBanner("");
		result.setCreditCard(Utiles.createCreditCard());
		result.setLinkTPage("");
		result.setSponsor(Utiles.createSponsor());
		result.setTutorial(Utiles.createTutorial());

		return result;
	}
	public static CreditCard createCreditCard() {
		CreditCard result;

		result = new CreditCard();
		result.setBrandName("");
		result.setNumber(0);
		result.setCodeCVV(1);
		result.setExpirationMonth(new Date());
		result.setExpirationYear(new Date());
		result.setType("");
		result.setHolderName("");

		return result;
	}
}
