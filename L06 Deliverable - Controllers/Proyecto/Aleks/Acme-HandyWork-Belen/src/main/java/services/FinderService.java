
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.CategoryRepository;
import repositories.FinderRepository;
import repositories.WarrantyRepository;
import utilities.Utiles;
import domain.Category;
import domain.Finder;
import domain.Warranty;

@Service
@Transactional
public class FinderService {

	@Autowired
	private FinderRepository	finderRepository;

	@Autowired
	private WarrantyRepository	warrantyRepository;

	@Autowired
	private CategoryRepository	categoryRepository;


	public Collection<Finder> findAll() {
		return this.finderRepository.findAll();
	}

	public Finder findOne(final int id) {
		return this.finderRepository.findOne(id);
	}

	public Finder save(final Finder f) {
		Warranty w;
		w = this.warrantyRepository.save(Utiles.createWarranty());
		w.setLaws("Ley1");
		w.setTerms("Term1");
		w.setTitle("warranty1");
		System.out.println(w);
		System.out.println(w.getTitle());
		f.setWarranty(w);
		Category c;
		c = this.categoryRepository.save(Utiles.createCategory());
		System.out.println(c);
		c.setName("Category1");
		f.setCategory(c);
		Finder modify;
		modify = this.finderRepository.save(f);
		return modify;
	}

}
