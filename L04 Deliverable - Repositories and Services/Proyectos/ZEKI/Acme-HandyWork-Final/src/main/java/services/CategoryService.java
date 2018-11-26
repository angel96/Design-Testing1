package services;

import java.util.ArrayList;
import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.mchange.util.AssertException;


import domain.Administrator;
import domain.Category;
import domain.Customer;

import repositories.CategoryRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;

@Service
@Transactional
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private AdministratorService adS;
	
	
	public Collection<Category> findAll() {
		return this.categoryRepository.findAll();
	}
	
	public Category findOne(final int id) {
		return this.categoryRepository.findOne(id);
	}
	
	public Category addCategory(final Category c) {
		UserAccount idLogged;
		idLogged = LoginService.getPrincipal();

		Assert.isTrue(Utiles.findAuthority(idLogged.getAuthorities(), Authority.ADMIN));
//		UserAccount user;
//		user = LoginService.getPrincipal();
//		Assert.notNull(user);
//
//		
//		Assert.isTrue(this.adS.findByUserAccount(user.getId()));
		
		Category result;
		Assert.notNull(c);
		result = this.categoryRepository.save(c);
		Assert.notNull(result);
		return result;
		

	}
	
	public Category createCategory() {
		UserAccount idLogged;
		idLogged = LoginService.getPrincipal();

		Assert.isTrue(Utiles.findAuthority(idLogged.getAuthorities(), Authority.ADMIN));
		Category c;
		c = new Category();
		
		c.setName("");
		c.setCategories(new ArrayList<String>());
		
		return c;
	}
	
	public Category updateCategory(final int id, final Category newer) {
		UserAccount idLogged;
		idLogged = LoginService.getPrincipal();

		Assert.isTrue(Utiles.findAuthority(idLogged.getAuthorities(), Authority.ADMIN));
		Category c;
		c = this.findOne(id);
		c.setName(newer.getName());
		c.setCategories(newer.getCategories());
		return this.categoryRepository.save(c);
	}
	
	public void deleteCategory(final int id) {
		UserAccount idLogged;
		idLogged = LoginService.getPrincipal();

		Assert.isTrue(Utiles.findAuthority(idLogged.getAuthorities(), Authority.ADMIN));
		Category c;
		c = this.findOne(id);
		this.categoryRepository.delete(c);
		
	}
	

}
