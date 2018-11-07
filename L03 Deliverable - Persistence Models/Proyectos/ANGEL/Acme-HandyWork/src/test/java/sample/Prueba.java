
package sample;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import utilities.internal.SchemaPrinter;

import com.google.gson.Gson;

import domain.Application;
import domain.Category;
import domain.FixUpTask;
import domain.Phase;
import domain.Warranty;

public class Prueba {

	@Test
	public void test() {
		final Category cat = new Category();
		cat.setName("Tests");
		final Warranty w = new Warranty();
		w.setTitle("Test warranty");
		w.setTerms("Anyone read terms..");
		final Phase p = new Phase();
		p.setTitle("Phase");
		p.setDescription("A description for the phase");
		p.setStartMoment(Date.valueOf("2018-10-20"));
		p.setEndMoment(Date.valueOf("2018-10-26"));
		p.setNumber(0);
		final List<Phase> ph = new ArrayList<>();
		ph.add(p);
		final Application app = new Application();
		app.setMoment(Date.valueOf("2018-10-31"));
		app.setOfferedPrice(1.0);
		app.setComments("A app comment");
		app.setStatus("ACCEPTED");
		final FixUpTask fix = new FixUpTask();
		fix.setTicker("181031-A8GT5G");
		fix.setMoment(Date.valueOf("2018-10-30"));
		fix.setDescrition("A FixUp description");
		fix.setAdress("Random Street");
		fix.setMaximumPrice(200);
		fix.setStart(Date.valueOf("2018-10-24"));
		fix.setEnd(Date.valueOf("2018-11-24"));
		fix.setCategory(cat);
		fix.setWarranty(w);
		fix.setPhases(ph);

		final Gson gson = new Gson();
		final String s = gson.toJson(fix);
		SchemaPrinter.print(s);
		System.out.println(s);
	}
}
