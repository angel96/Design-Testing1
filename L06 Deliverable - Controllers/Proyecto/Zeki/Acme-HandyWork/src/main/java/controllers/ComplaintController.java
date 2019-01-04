
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.ComplaintService;
import services.FixUpTaskService;
import utilities.Utiles;
import domain.Complaint;

@Controller
@RequestMapping("/complaint/customer,handyworker,referee")
public class ComplaintController extends AbstractController {

	@Autowired
	private ComplaintService	serviceComplaint;

	@Autowired
	private FixUpTaskService	serviceFix;


	public ComplaintController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listComplaintCustomer() {
		ModelAndView result;
		result = new ModelAndView("complaint/list");
		if (LoginService.getPrincipal().getAuthorities().toString().equals("[CUSTOMER]"))
			result.addObject("complaints", this.serviceComplaint.findComplaintByCustomer(this.serviceComplaint.getActorByUser(LoginService.getPrincipal().getId())));
		else if (LoginService.getPrincipal().getAuthorities().toString().equals("[HANDY_WORKER]"))
			result.addObject("complaints", this.serviceComplaint.findComplaintByHandyWorkerId(this.serviceComplaint.getActorByUser(LoginService.getPrincipal().getId()).getId()));
		else if (LoginService.getPrincipal().getAuthorities().toString().equals("[REFEREE]"))
			result.addObject("complaints", this.serviceComplaint.findComplaintByReferee(this.serviceComplaint.getActorByUser(LoginService.getPrincipal().getId()).getId()));

		result.addObject("requestURI", "complaint/customer,handyworker,referee/list.do");
		return result;
	}

	@RequestMapping(value = "/listNoAssigned", method = RequestMethod.GET)
	public ModelAndView listComplaintCustomerNoAssigned() {
		final ModelAndView model;
		model = new ModelAndView("complaint/list");
		if (LoginService.getPrincipal().getAuthorities().toString().equals("[REFEREE]"))
			model.addObject("complaints", this.serviceComplaint.findComplaintNoRefereeAssigned());

		model.addObject("requestURI", "complaint/customer,handyworker,referee/list.do");
		return model;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createComplaint(@RequestParam final int id, @RequestParam final String view) {
		ModelAndView result;
		System.out.println(id);
		System.out.println(view);
		result = this.createEditModelAndView(Utiles.createComplaint(), view);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@Valid final Complaint complaint, final BindingResult binding, final String view) {
		ModelAndView model;

		System.out.println("la id es:" + view);
		if (binding.hasErrors()) {
			model = this.createEditModelAndView(complaint, view);
			model.addObject("errores", binding.getAllErrors());
			model.addObject("errores", binding);
		} else
			try {
				this.serviceComplaint.save(complaint);
				model = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				model = this.createEditModelAndView(complaint, "complaint.commit.error", view);
				model.addObject("oops", oops.getMessage());
				model.addObject("errors", binding.getAllErrors());
			}
		return model;
	}
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView viewComplaint(@RequestParam final int id, @RequestParam final String view) {
		ModelAndView result;
		Complaint find;
		System.out.println(id);
		System.out.println(view);
		find = this.serviceComplaint.findOne(id);
		result = this.createEditModelAndView(find, view);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Complaint complaint, final String view) {
		ModelAndView result;
		System.out.println(view);
		result = this.createEditModelAndView(complaint, null, view);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Complaint complaint, final String message, final String view) {
		ModelAndView result;
		System.out.println(view);
		result = new ModelAndView("complaint/edit");
		result.addObject("complaint", complaint);
		result.addObject("message", message);
		result.addObject("view", view);
		return result;
	}
}
