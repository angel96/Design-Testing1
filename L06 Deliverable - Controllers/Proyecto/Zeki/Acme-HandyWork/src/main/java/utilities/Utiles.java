
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
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.util.Assert;

import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.internal.SchemaPrinter;
import domain.Actor;
import domain.Administrator;
import domain.Application;
import domain.Box;
import domain.Category;
import domain.Complaint;
import domain.CreditCard;
import domain.Curriculum;
import domain.Endorsable;
import domain.Endorsement;
import domain.Finder;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Message;
import domain.Note;
import domain.Phase;
import domain.Profile;
import domain.Section;
import domain.Sponsor;
import domain.Sponsorship;
import domain.Tutorial;
import domain.Warranty;

public class Utiles {

	public static String hashPassword(final String old) {
		Md5PasswordEncoder encoder;
		encoder = new Md5PasswordEncoder();
		String passEncoded;
		passEncoded = encoder.encodePassword(old, null);

		return passEncoded;
	}
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

	public static Date convertDate(final int year, final int month, final int day) {
		Date d;
		d = new Date(year - 1900, month - 1, day);
		return d;
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

		List<?> result;
		result = jpaQuery.getResultList();
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

	public static void sendIndividualMessage(/* final Actor sender, */final Actor recipient, final Collection<Message> received, final Message send) {
		Collection<Box> boxes;
		Collection<Message> total;
		boxes = recipient.getBoxes();
		received.add(send);
		for (final Box b : boxes)
			if (b.getName().equals("entry")) {
				total = b.getMessage();
				total.addAll(received);
			}
	}

	//	public static Boolean checkCollectionsSpam(final List<SpamWord> spam, final String[]... fields) {
	//		Boolean res = false;
	//		for (final String[] s : fields)
	//			//			for (int i = 0; i < spam.size(); i++)
	//			for (final SpamWord sw : spam)
	//				if (sw.getWord().equals(s.toString())) {
	//					res = true;
	//					break;
	//				}
	//		return res;
	//	}
	//	public static Boolean checkSpamStrings(final List<SpamWord> spam, final String s) {
	//		Boolean res = false;
	//		for (final SpamWord sw : spam)
	//			if (sw.getWord().equals(s)) {
	//				res = true;
	//				break;
	//			}
	//		return res;
	//	}
	public static Administrator createAdministrator() {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));
		Administrator administrator;
		administrator = new Administrator();

		UserAccount user;
		user = new UserAccount();
		user.setUsername("");
		user.setPassword("");

		Authority authority;
		authority = new Authority();
		authority.setAuthority(Authority.ADMIN);
		user.addAuthority(authority);

		administrator.setAccount(user);
		administrator.setProfiles(new ArrayList<Profile>());
		administrator.setAdress("");
		administrator.setBan(false);
		administrator.setBoxes(new ArrayList<Box>());
		administrator.setEmail("");
		administrator.setMiddleName("");
		administrator.setName("");
		administrator.setPhone("");
		administrator.setPhoto("");
		administrator.setSurname("");
		System.out.println(administrator);

		return administrator;
	}

	public static HandyWorker createHandyWorker() {
		HandyWorker handyWorker;
		handyWorker = new HandyWorker();
		UserAccount account;
		account = new UserAccount();
		Authority auth;
		auth = new Authority();
		Collection<Authority> authorities;
		authorities = new ArrayList<>();
		auth.setAuthority(Authority.HANDY_WORKER);
		authorities.add(auth);
		account.setAuthorities(authorities);
		account.setUsername("");
		account.setPassword("");
		handyWorker.setAccount(account);
		handyWorker.setApplication(new ArrayList<Application>());
		handyWorker.setNotes(new ArrayList<Note>());
		handyWorker.setPhase(new ArrayList<Phase>());
		handyWorker.setProfiles(new ArrayList<Profile>());
		handyWorker.setTutoriales(new ArrayList<Tutorial>());
		handyWorker.setAdress("");
		handyWorker.setBan(false);
		handyWorker.setCurriculum(new Curriculum());
		handyWorker.setEmail("");
		handyWorker.setMiddleName("");
		handyWorker.setName("");
		handyWorker.setPhone("");
		handyWorker.setPhoto("");
		handyWorker.setSurname("");
		handyWorker.setFinder(new Finder());
		handyWorker.setMake("");
		handyWorker.setProfiles(new ArrayList<Profile>());
		handyWorker.setBoxes(new ArrayList<Box>());
		handyWorker.setScore(5.0);
		System.out.println(handyWorker);
		return handyWorker;
	}
	public static Finder createFinder() {
		Finder f;
		f = new Finder();
		f.setCategory(new Category());
		f.setCreationDate(new Date());
		f.setEndDate(new Date());
		f.setFixUpTask(new ArrayList<FixUpTask>());
		f.setPrice1(0.0);
		f.setPrice2(10000.0);
		f.setSingleKey("");
		f.setStartDate(new Date());
		f.setWarranty(new Warranty());
		return f;
	}

	public static Profile createProfile() {
		Profile p;
		p = new Profile();
		p.setNick("");
		p.setSocialNetworkName("");
		p.setLink("");
		return p;
	}

	public static Phase createPhase() {
		Phase p;
		p = new Phase();
		p.setDescription("");
		p.setEndMoment(new Date());
		p.setNumber(0);
		p.setStartMoment(new Date());
		p.setTitle("");
		return p;
	}
	public static Category createCategory() {
		Category c;
		c = new Category();

		c.setName("");
		c.setCategories(new ArrayList<String>());

		return c;
	}
	public static Application createApplication() {
		Application a;
		a = new Application();
		a.setFixUpTask(new FixUpTask());
		a.setMoment(new Date());
		a.setMomentElapsed(new Date());
		a.setOfferedPrice(0.0);
		a.setStatus("pending");

		return a;
	}
	public static FixUpTask createFixUpTask() {

		FixUpTask fut;
		fut = new FixUpTask();
		fut.setAddress("");
		fut.setApplication(new ArrayList<Application>());
		fut.setCategory(new Category());
		fut.setComplaint(new ArrayList<Complaint>());
		fut.setDescription("");
		fut.setEnd(new Date());
		fut.setMaximumPrice(0.0);
		fut.setMoment(new Date());
		fut.setPhases(new ArrayList<Phase>());
		fut.setWarranty(new Warranty());
		fut.setTicker(Utiles.generateTicker());
		return fut;
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
	//	public static Word createWord() {
	//		Word w;
	//		w = new Word();
	//		w.setWord("");
	//		w.setIsGood(true);
	//		return w;
	//	}
	//Authenticated as Customer or HandyWorker
	public static Endorsement createEndorsement(final Endorsable send, final Endorsable receive) {
		Endorsement e;
		e = new Endorsement();
		e.setMoment(new Date());
		e.setUserReceived(receive);
		e.setUserSended(send);
		e.setComments(new ArrayList<String>());
		return e;
	}
	public static Sponsorship createSponsorship(final Sponsor sponsor, final Tutorial tutorial) {
		Sponsorship result;
		result = new Sponsorship();

		result.setUrlBanner("");
		result.setCreditCard(Utiles.createCreditCard());
		result.setLinkTPage("");
		result.setSponsor(sponsor);
		result.setTutorial(tutorial);

		return result;
	}
	public static CreditCard createCreditCard() {
		CreditCard result;

		result = new CreditCard();
		result.setBrandName("");
		result.setNumber(1);
		result.setCodeCVV(100);
		result.setExpirationMonth(new Date());
		result.setExpirationYear(new Date());
		result.setType("");
		result.setHolderName("");

		return result;
	}
	public static Boolean findAuthority(final Collection<Authority> comp, final String a) {
		Boolean res = false;
		if (comp.size() > 1) {
			Authority aut;
			aut = new Authority();
			aut.setAuthority(a);
			res = comp.contains(aut);
		} else
			for (final Authority authority : comp)
				if (authority.toString().equals(a))
					res = true;

		return res;
	}
}
