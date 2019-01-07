
package utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import domain.Customer;
import domain.Endorsable;
import domain.Endorsement;
import domain.Finder;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Mesage;
import domain.Note;
import domain.Phase;
import domain.Profile;
import domain.Referee;
import domain.Report;
import domain.Section;
import domain.Sponsor;
import domain.Sponsorship;
import domain.Tutorial;
import domain.Warranty;

public class Utiles {

	public static Collection<String>	spamWords	= new ArrayList<String>();
	public static Collection<String>	goodWords	= new ArrayList<String>();
	public static Collection<String>	badWords	= new ArrayList<String>();

	public static Integer				hoursFinder;
	public static Integer				resultsFinder;
	public static Double				vat;
	public static Integer				phonePrefix;


	public static void setParametersFinder(final Integer hours, final Integer results, final Double vat, final Integer phonePrefix) {
		Utiles.hoursFinder = hours;
		Utiles.resultsFinder = results;
		Utiles.vat = vat;
		Utiles.phonePrefix = phonePrefix;
	}

	public static Collection<String> limpiaString(String s) {
		s = s.replaceAll("[^a-zA-Z0-9]", "#");
		final List<String> textoRoto = Arrays.asList(s.split("##|#"));
		return textoRoto;
	}

	public static boolean spamWord(final Collection<String> contentMessage) {
		boolean res = false;
		for (final String word : contentMessage)
			if (Utiles.spamWords.contains(word))
				res = true;
		return res;
	}

	public static Collection<Box> initBoxes() {

		List<Box> boxesSystem;

		boxesSystem = new ArrayList<Box>();

		boxesSystem.add(Utiles.createBox(true, "In Box"));
		boxesSystem.add(Utiles.createBox(true, "Out Box"));
		boxesSystem.add(Utiles.createBox(true, "Spam Box"));
		boxesSystem.add(Utiles.createBox(true, "Trash Box"));

		return boxesSystem;

	}

	public static Box createBox(final boolean fromSystem, final String name) {

		Box b;

		b = new Box();

		b.setMessage(new ArrayList<Mesage>());

		b.setFromSystem(fromSystem);

		b.setName(name);

		return b;

	}

	public static String[] checkCreditCard(final String cadena) {
		/**
		 * This method is implemented according to the Luhn Algorithm
		 * https://www.journaldev.com/1443/java-credit-card-validation-luhn-algorithm-java
		 * https://howtodoinjava.com/regex/java-regex-validate-credit-card-numbers/
		 */

		/**
		 * This string array only have two values
		 * 1. Type of creditCard
		 * 2. It is a legal credit card or none.
		 */
		String[] result;
		result = new String[2];

		/**
		 * This makes a little repository of patterns that checks directly which kind of credit card is given by cadena´s parameter.
		 */
		Map<String, Matcher> map;
		map = new HashMap<String, Matcher>();

		map.put("VISA", Pattern.compile("^4[0-9]{12}(?:[0-9]{3})?$").matcher(cadena));
		map.put("MASTERCARD", Pattern.compile("^5[1-5][0-9]{14}$").matcher(cadena));
		map.put("AMEX", Pattern.compile("^3[47][0-9]{13}$").matcher(cadena));
		map.put("DINERS", Pattern.compile("^3(?:0[0-5]|[68][0-9])?[0-9]{11}$").matcher(cadena));

		if (map.get("VISA").matches())
			result[0] = "VISA";
		else if (map.get("MASTERCARD").matches())
			result[0] = "MASTERCARD";
		else if (map.get("AMEX").matches())
			result[0] = "AMEX";
		else if (map.get("DINERS").matches())
			result[0] = "DINERS";

		/**
		 * Luhn Algorithm
		 */
		result[1] = Boolean.toString(Utiles.luhnAlgorithm(cadena));

		return result;

	}

	private static boolean luhnAlgorithm(final String cadena) {
		final int[] str = new int[cadena.length()];

		for (int i = 0; i < str.length; i++)
			str[i] = Integer.parseInt(cadena.substring(i, i + 1));

		for (int i = str.length - 2; i >= 0; i = i - 2) {

			int j = str[i];
			j = j * 2;

			if (j > 9)
				j = j % 10 + 1;

			str[i] = j;

		}
		int sum = 0;

		for (int i = 0; i < str.length; i++)
			sum = sum + str[i];

		return sum % 10 == 0;
	}

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

		String formated;

		formated = formato.format(new Date());

		final Character[] ch = {
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
		};

		String c = "";

		Random random;

		random = new Random();

		for (int i = 0; i < 6; i++)
			c += ch[random.nextInt(ch.length)];

		return formated + "-" + c;
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
	public static Profile createProfile() {
		Profile result;
		result = new Profile();
		result.setNick("");
		result.setLink("");
		result.setSocialNetworkName("");
		return result;
	}
	public static Complaint createComplaint() {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.CUSTOMER));
		Complaint res;
		res = new Complaint();
		res.setTicker(Utiles.generateTicker());
		res.setMoment(new Date());
		res.setReport(new ArrayList<Report>());
		res.setDescription("");
		res.setAttachment(0);
		res.setReferee(new Referee());
		//		FixUpTask f;
		//		f = this.serviceFix.findOne(id);
		//		Collection<Complaint> l;
		//		l = f.getComplaint();
		//		l.add(comp);
		//		f.setComplaint(l);
		return res;
	}
	public static Referee createReferee() {

		UserAccount creator;
		creator = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(creator.getAuthorities(), Authority.ADMIN));

		Referee r;
		r = new Referee();

		UserAccount user;
		user = new UserAccount();
		Authority a;
		a = new Authority();
		Collection<Authority> authorities;
		authorities = new ArrayList<>();
		a.setAuthority(Authority.REFEREE);
		authorities.add(a);
		user.setAuthorities(authorities);

		r.setAccount(user);
		r.setAdress("");
		r.setBan(false);
		r.setBoxes(new ArrayList<Box>());
		r.setEmail("");

		r.setMiddleName("");
		r.setName("");
		r.setNotes(new ArrayList<Note>());
		r.setPhone("");
		r.setPhoto("");
		r.setProfiles(new ArrayList<Profile>());
		r.setSurname("");
		return r;
	}
	public static Note createNote() {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue((Utiles.findAuthority(user.getAuthorities(), Authority.CUSTOMER) || Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER) || Utiles.findAuthority(user.getAuthorities(), Authority.REFEREE)));
		Note res;
		res = new Note();
		res.setMoment(new Date());
		res.setComment("");
		res.setOtherComments(new ArrayList<String>());
		return res;
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
		handyWorker.setScore(0.0);
		handyWorker.setSurname("");

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
	public static Mesage createMessage(final Actor a) {
		Mesage message;
		message = new Mesage();

		message.setSender(a);
		message.setBody("");
		message.setMoment(new Date());
		message.setTags(new ArrayList<String>());
		message.setSubject("");

		message.setPriority("NEUTRAL");
		message.setBox(new ArrayList<Box>());
		message.setReceiver(new ArrayList<Actor>());

		return message;
	}
	public static Customer createCustomer() {

		Customer customer;
		customer = new Customer();

		UserAccount account;
		account = new UserAccount();
		Authority auth;
		auth = new Authority();
		Collection<Authority> authorities;
		authorities = new ArrayList<>();
		auth.setAuthority(Authority.CUSTOMER);
		authorities.add(auth);
		account.setUsername("");
		account.setPassword("");
		account.setAuthorities(authorities);

		customer.setAccount(account);
		customer.setComplaint(new ArrayList<Complaint>());
		customer.setFixUpTask(new ArrayList<FixUpTask>());
		customer.setNotes(new ArrayList<Note>());
		customer.setProfiles(new ArrayList<Profile>());
		customer.setAdress("");

		customer.setEmail("");

		customer.setMiddleName("");
		customer.setName("");
		customer.setPhone("");
		customer.setPhoto("");
		customer.setScore(0.0);
		customer.setSurname("");

		return customer;
	}

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
		administrator.setBoxes(new ArrayList<Box>());
		administrator.setEmail("");
		administrator.setMiddleName("");
		administrator.setName("");
		administrator.setPhone("");
		administrator.setPhoto("");
		administrator.setSurname("");

		return administrator;
	}

	public static Phase createPhase() {
		Phase p;
		p = new Phase();
		p.setDescription("");
		p.setEndMoment(new Date());
		p.setNumber(1);
		p.setStartMoment(new Date());
		p.setTitle("");
		return p;
	}
	public static Category createCategory() {
		Category c;
		c = new Category();

		c.setName("");
		c.setCategories(new ArrayList<Category>());

		return c;
	}
	public static Application createApplication(final FixUpTask f) {
		Application a;
		a = new Application();
		a.setFixUpTask(f);
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
		result.setExpiration(new Date());

		result.setType("");
		result.setHolderName("");

		return result;
	}
	public static String[] priorities() {
		String[] priorities;
		priorities = new String[3];

		Arrays.fill(priorities, "");

		priorities[0] = "LOW";
		priorities[1] = "NEUTRAL";
		priorities[2] = "HIGH";

		return priorities;
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
