
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import utilities.Utiles;
import domain.Section;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TestSectionService extends AbstractTest {

	@Autowired
	private SectionService	sectionService;


	@Test
	public void testGetAll1() {
		Assert.isTrue(this.sectionService.findAll().size() >= 1);
	}

	@Test
	public void testCreateOnDatabase2() {
		Section s, saved;
		s = Utiles.createSection();
		s.setTitle("Section Test 1");
		s.setText("Section Test 1 Texto");
		s.setNumber(1);
		List<String> picture;
		picture = new ArrayList<>();
		picture.add("Image 1");
		picture.add("Image 2");
		picture.add("Image 3");
		s.setPicture(picture);
		saved = this.sectionService.addSection(s);
		Assert.isTrue(this.sectionService.findAll().contains(saved));
	}
	@Test
	public void testGetById3() {
		Assert.notNull(this.sectionService.findById(1247));
	}
	@Test
	public void testDeleteSection4() {
		Section s;
		s = this.sectionService.findById(1247);
		final int id = s.getId();
		this.sectionService.deleteSection(s);
		Assert.isNull(this.sectionService.findById(id));
	}
	@Test
	public void testUpdateSection5() {
		Section nonUpdate;
		nonUpdate = this.sectionService.findById(1247);
		Section update;
		update = Utiles.createSection();
		update.setTitle("parteTest2");
		update.setText("parteTest2Text");
		Collection<String> pictures;
		pictures = nonUpdate.getPicture();
		List<String> auxiliarPictures;
		auxiliarPictures = new ArrayList<>();
		for (final String s : pictures)
			auxiliarPictures.add(s + "TestUpdate");
		update.setPicture(pictures);
		final Section s = this.sectionService.updateSection(nonUpdate.getId(), update);
		Assert.notNull(s);
	}

}
