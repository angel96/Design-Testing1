
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import utilities.Utiles;
import domain.Word;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestWordService extends AbstractTest {

	@Autowired
	private WordService	wordService;


	@Test
	public void testGetAll() {
		Assert.isTrue(this.wordService.getAll().size() >= 1);
	}

	public void testGetAllGood() {
		Assert.isTrue(this.wordService.findGoodWords().size() >= 1);
	}
	@Test
	public void testGetAllBad() {
		Assert.isTrue(this.wordService.findBadWords().size() >= 1);
	}
	@Test
	public void testCreateWord() {
		super.authenticate("admin1");
		Word w, saved;
		w = Utiles.createWord();
		w.setIsGood(true);
		w.setWord("Vida");
		saved = this.wordService.addWord(w);
		Assert.isTrue(this.wordService.getAll().contains(saved));
		super.unauthenticate();
	}
	@Test
	public void testDeleteWord() {
		super.authenticate("admin1");
		this.wordService.deleteWord(2916);
		Assert.isNull(this.wordService.findById(1115));
		super.unauthenticate();
	}
}
