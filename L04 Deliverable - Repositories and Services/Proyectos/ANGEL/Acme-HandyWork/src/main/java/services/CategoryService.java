
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Category;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository		categoryRepository;

	@Autowired
	private AdministratorService	adminService;


	public Collection<Category> findAll() {
		return this.categoryRepository.findAll();
	}

	public Category findOne(final int id) {
		return this.categoryRepository.findOne(id);
	}

	public Category addCategory(final Category c) {
		UserAccount idLogged;
		idLogged = LoginService.getPrincipal();

		Administrator admin;
		admin = this.adminService.findByUserAccount(idLogged.getId());

		Assert.notNull(admin);

		Category result;
		Assert.notNull(c);
		result = this.categoryRepository.save(c);
		Assert.notNull(result);
		return result;

	}

	public Category updateCategory(final Category newer) {
		UserAccount idLogged;
		idLogged = LoginService.getPrincipal();

		Administrator admin;
		admin = this.adminService.findByUserAccount(idLogged.getId());

		Assert.notNull(admin);

		return this.categoryRepository.save(newer);
	}

	public void deleteCategory(final int id) {
		UserAccount idLogged;
		idLogged = LoginService.getPrincipal();

		Administrator admin;
		admin = this.adminService.findByUserAccount(idLogged.getId());

		Assert.notNull(admin);

		Category c;
		c = this.findOne(id);
		this.categoryRepository.delete(c);

	}

}
