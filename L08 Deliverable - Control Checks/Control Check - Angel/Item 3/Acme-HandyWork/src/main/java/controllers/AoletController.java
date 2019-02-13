
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.LoginService;
import services.AoletService;
import utilities.Utiles;
import domain.Aolet;
import domain.FixUpTask;

@Controller
@RequestMapping(value = {
	"aolet/customer", "aolet/handyworker"
})
public class AoletController extends AbstractController {

	@Autowired
	private AoletService	service;


	//CRUD
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(final int fixuptask, @CookieValue(value = "language", required = false) final String lang) {
		ModelAndView result;
		result = new ModelAndView("aolet/list");
		if (lang == null)
			result.addObject("lang", "en");
		else
			result.addObject("lang", lang);

		result.addObject("aolets", this.service.getAoletsByFixUpTask(fixuptask));
		return result;
	}

	//Create
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(final int fixuptask) {
		ModelAndView result;
		result = this.createEditModelAndView(fixuptask, this.service.create());
		result.addObject("requestURI", "aolet/customer/edit.do?fixuptask=" + fixuptask);
		return result;
	}

	//findOne
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {

		ModelAndView result;
		final Aolet aolet = this.service.findOne(id);
		result = this.createEditModelAndView(0, aolet);
		boolean mine;
		if (Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.CUSTOMER)) {
			mine = this.service.getAoletsUserLogged().contains(aolet);
			result.addObject("requestURI", "aolet/customer/edit.do?id=" + id);
		} else {
			mine = false;
			result.addObject("requestURI", "aolet/handyworker/edit.do?id=" + id);
		}
		result.addObject("mine", mine);

		return result;
	}

	//Save & Update
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@RequestParam(required = false, defaultValue = "0") final Integer fixuptask, @Valid final Aolet aolet, final BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(fixuptask, aolet, null);
			result.addObject("errors", binding.getAllErrors());
		} else
			try {

				//Here goes service code
				this.service.save(aolet, fixuptask);
				//Redirection to list			
				result = new ModelAndView("redirect:list.do?fixuptask=" + fixuptask);

			} catch (final Exception oops) {
				//Save in message file the reference to entity.commit.error
				result = this.createEditModelAndView(fixuptask, aolet, "aolet.commit.error");
				result.addObject("oops", oops.getMessage());
				result.addObject("errors", binding.getAllErrors());
			}

		return result;

	}

	//Delete
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView edit(@RequestParam(required = false, defaultValue = "0") final int fixuptask, final Aolet aolet) {

		ModelAndView result;

		try {
			final FixUpTask f = this.service.fixUpByAolet(aolet.getId());
			//Delete code
			this.service.delete(aolet);
			result = new ModelAndView("redirect:list.do?fixuptask=" + f.getId());
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(fixuptask, aolet, "aolet.commit.error");
		}
		return result;
	}

	//Protected Methods
	protected ModelAndView createEditModelAndView(final int fixuptask, final Aolet aolet) {

		ModelAndView result;

		result = this.createEditModelAndView(fixuptask, aolet, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final int fixuptask, final Aolet aolet, final String message) {

		ModelAndView result;

		result = new ModelAndView("aolet/edit");
		result.addObject("aolet", aolet);
		result.addObject("message", message);
		return result;
	}
}
