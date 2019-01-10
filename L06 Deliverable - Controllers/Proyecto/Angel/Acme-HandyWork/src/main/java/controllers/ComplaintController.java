
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
import domain.Complaint;

@Controller
@RequestMapping(value = {
	"/complaint/customer", "/complaint/handyworker", "/complaint/referee"
})
public class ComplaintController extends AbstractController {

	@Autowired
	private ComplaintService	serviceComplaint;


	public ComplaintController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listComplaintCustomer(@RequestParam(defaultValue = "false") final Boolean ref) {
		ModelAndView result;
		result = new ModelAndView("complaint/list");
		if (LoginService.getPrincipal().getAuthorities().toString().equals("[CUSTOMER]")) {
			result.addObject("complaints", this.serviceComplaint.findComplaintByCustomer(this.serviceComplaint.getActorByUser(LoginService.getPrincipal().getId())));
			result.addObject("requestURI", "complaint/customer/list.do");

		} else if (LoginService.getPrincipal().getAuthorities().toString().equals("[HANDY_WORKER]")) {
			result.addObject("complaints", this.serviceComplaint.findComplaintByHandyWorkerId(this.serviceComplaint.getActorByUser(LoginService.getPrincipal().getId()).getId()));
			result.addObject("requestURI", "complaint/handyworker/list.do");

		} else if (LoginService.getPrincipal().getAuthorities().toString().equals("[REFEREE]") && ref == true) {
			result.addObject("complaints", this.serviceComplaint.findComplaintByReferee(this.serviceComplaint.getActorByUser(LoginService.getPrincipal().getId()).getId()));
			result.addObject("requestURI", "complaint/referee/list.do");

		} else if (LoginService.getPrincipal().getAuthorities().toString().equals("[REFEREE]") && ref == false) {
			result.addObject("complaints", this.serviceComplaint.findComplaintNoRefereeAssigned());
			result.addObject("requestURI", "complaint/referee/list.do");
		}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createComplaint(@RequestParam final int idFix, @RequestParam(defaultValue = "false") final String view) {
		ModelAndView result;
		System.out.println(idFix);
		System.out.println(view);
		result = this.createEditModelAndView(this.serviceComplaint.createComplaint());
		result.addObject("view", view);
		result.addObject("idFix", idFix);
		System.out.println(result);
		result.addObject("requestURI", "complaint/customer/edit.do");
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@RequestParam final int idFix, @Valid final Complaint complaint, final BindingResult binding) {
		ModelAndView model;

		System.out.println("edit" + idFix);
		if (binding.hasErrors()) {
			model = this.createEditModelAndView(complaint);
			model.addObject("errores", binding.getAllErrors());
			model.addObject("errores", binding);
		} else
			try {

				this.serviceComplaint.save(complaint, idFix);
				model = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				model = this.createEditModelAndView(complaint, "complaint.commit.error");
				model.addObject("oops", oops.getMessage());
				model.addObject("errors", binding.getAllErrors());
			}
		return model;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updateComplaint(@RequestParam final int id) {
		ModelAndView result;
		this.serviceComplaint.update(id);
		result = new ModelAndView("redirect:list.do");
		return result;
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView viewComplaint(@RequestParam final int id, @RequestParam(defaultValue = "false") final String view) {
		ModelAndView result;
		Complaint find;
		System.out.println(id);
		System.out.println(view);
		find = this.serviceComplaint.findOne(id);
		result = this.createEditModelAndView(find);
		result.addObject("view", view);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Complaint complaint) {
		ModelAndView result;
		result = this.createEditModelAndView(complaint, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Complaint complaint, final String message) {
		ModelAndView result;
		result = new ModelAndView("complaint/edit");
		result.addObject("complaint", complaint);
		result.addObject("message", message);
		return result;
	}
}
