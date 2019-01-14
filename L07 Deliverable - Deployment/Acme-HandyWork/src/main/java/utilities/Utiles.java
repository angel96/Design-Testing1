
package utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import security.Authority;
import domain.Box;
import domain.CreditCard;
import domain.Endorsement;
import domain.Mesage;

public class Utiles {

	public static void main(final String[] args) {
		final String s = "select f from FixUpTask f where  f.start >= '2018/12/12' AND f.end <= '2018/12/30' AND";
		System.out.println(s.endsWith("AND"));

		System.out.println(s);

	}


	public static Collection<String>	spamWords	= new ArrayList<String>();
	public static Collection<String>	goodWords	= new ArrayList<String>();
	public static Collection<String>	badWords	= new ArrayList<String>();

	public static Integer				hoursFinder;
	public static Integer				resultsFinder;
	public static Double				vat;
	public static Integer				phonePrefix;

	public static String				statusTEMP	= "";


	public static double homotheticalTransformation(final Collection<Endorsement> endorsements) {

		Double res = 0.0;

		List<Double> good;
		good = new ArrayList<Double>();
		List<Double> bad;
		bad = new ArrayList<Double>();

		for (final Endorsement e : endorsements) {
			double p = 0.;
			double n = 0.;
			for (final String s : Utiles.limpiaString(e.getComments().toString())) {
				System.out.println(s);
				if (Utiles.goodWords.contains(s))
					p++;
				if (Utiles.badWords.contains(s))
					n++;
			}
			good.add(p);
			bad.add(n);
		}

		if (Double.isNaN(Utiles.compute(good)) || Double.isNaN(Utiles.compute(bad)))
			res = 0.0;
		else
			res = Utiles.compute(good) - Utiles.compute(bad);

		return res;
	}

	private static double compute(final Collection<Double> values) {

		final int a = -1;
		final int b = 1;

		final double min = Collections.min(values);
		final double max = Collections.max(values);

		double z = 0.;

		for (final double d : values)
			z = z + a + ((d - min) * (b - a) / (max - min));

		return z;
	}
	public static Double priceWithVat(final Double d) {
		return Utiles.vat * d + d;
	}

	public static double[] convertToArrayDoubleFromString(final String s) {
		final String[] partes = s.split(",");
		final double[] result = new double[partes.length];
		for (int i = 0; i < partes.length; i++)
			result[i] = Double.valueOf(partes[i]);
		return result;
	}

	public static void setParameters(final Integer hours, final Integer results, final Double vat, final Integer phonePrefix) {
		Utiles.hoursFinder = hours;
		Utiles.resultsFinder = results;
		Utiles.vat = vat;
		Utiles.phonePrefix = phonePrefix;
		System.setProperty("hoursFinder", String.valueOf(hours * 60 * 60 * 1000));
	}

	public static Collection<String> limpiaString(String s) {
		s = s.replaceAll("[^a-zA-Z0-9$]", "#");

		final List<String> textoRoto = Arrays.asList(s.split("##|#"));
		return textoRoto;
	}
	public static boolean spamWord(final Collection<String> contentMessage) {
		boolean res = false;

		Map<String, Boolean> result;
		result = new HashMap<>();

		for (final String word : Utiles.spamWords)
			result.put(word, contentMessage.contains(word));

		for (final Boolean b : result.values())
			if (b) {
				res = true;
				break;
			}

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
	/*
	 * public static void fullTextSearch(final String s) {
	 * final HibernatePersistenceProvider provider = new HibernatePersistenceProvider();
	 * final EntityManagerFactory entityManagerFactory = provider.createEntityManagerFactory("Acme-HandyWork", null);
	 * final EntityManager em = entityManagerFactory.createEntityManager();
	 * final FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
	 * em.getTransaction().begin();
	 * 
	 * final QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(FixUpTask.class).get();
	 * 
	 * final org.apache.lucene.search.Query luceneQuery = qb.keyword().onFields("ticker", "description", "address").matching(s).createQuery();
	 * 
	 * // From Lucene query to Javax query
	 * final javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, FixUpTask.class);
	 * 
	 * List<?> result;
	 * result = jpaQuery.getResultList();
	 * SchemaPrinter.print(result);
	 * 
	 * em.getTransaction().commit();
	 * em.close();
	 * 
	 * }
	 */
	public static CreditCard createFakeCreditCard() {
		CreditCard c;
		c = new CreditCard();
		c.setBrandName("Currufur");
		c.setCodeCVV(254);

		final DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		final String date = "2022/04/01";
		try {
			c.setExpiration(format.parse(date));
		} catch (final ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		c.setHolderName("Prueba");
		c.setNumber("4514971809394094");
		c.setType("VISA");

		return c;
	}
	public static String[] status() {
		String[] status;
		status = new String[3];

		Arrays.fill(status, "");

		status[0] = "pending";
		status[1] = "accepted";
		status[2] = "rejected";

		return status;
	}
	public static Map<String, String> statusTranslation() {
		Map<String, String> map;
		map = new HashMap<String, String>();
		map.put(Utiles.status()[0], "pendiente");
		map.put(Utiles.status()[1], "aceptada");
		map.put(Utiles.status()[2], "rechazada");
		return map;
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
	public static String[] cards() {
		String[] priorities;
		priorities = new String[4];

		Arrays.fill(priorities, "");

		priorities[3] = "MASTER";
		priorities[1] = "VISA";
		priorities[2] = "DINERS";
		priorities[0] = "AMEX";

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
