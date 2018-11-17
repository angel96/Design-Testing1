
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Word;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestWordService {

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
		Word w;
		w = this.wordService.create();
		w.setIsGood(true);
		w.setWord("Vida");
		Assert.notNull(this.wordService.addWord(w));
	}
	@Test
	public void testDeleteWord() {
		this.wordService.deleteWord(1115);
		Assert.isNull(this.wordService.findById(1115));
	}
}
