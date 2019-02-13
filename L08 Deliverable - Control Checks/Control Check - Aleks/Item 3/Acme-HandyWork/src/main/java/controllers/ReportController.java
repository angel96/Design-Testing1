
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.ReportService;
import domain.Report;

@Controller
@RequestMapping(value = {
	"/report/referee", "/report/handyworker", "/report/customer"
})
public class ReportController extends AbstractController {

	@Autowired
	private ReportService	reportService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listReport() {
		ModelAndView result;
		result = new ModelAndView("report/list");
		if (LoginService.getPrincipal().getAuthorities().toString().equals("[REFEREE]")) {
			result.addObject("requestURI", "report/referee/list.do");
			result.addObject("reports", this.reportService.findReportsByReferee(this.reportService.getActorByUser(LoginService.getPrincipal().getId())));
		} else if (LoginService.getPrincipal().getAuthorities().toString().equals("[HANDY_WORKER]")) {
			result.addObject("requestURI", "report/handyworker/list.do");
			result.addObject("reports", this.reportService.findReportsByhandyWorkerId(this.reportService.findHandyByUserAccountId(LoginService.getPrincipal().getId()).getId()));
		} else if (LoginService.getPrincipal().getAuthorities().toString().equals("[CUSTOMER]")) {
			result.addObject("requestURI", "report/customer/list.do");
			result.addObject("reports", this.reportService.findReportsByCustomerId(this.reportService.findCustomerByUserAccountId(LoginService.getPrincipal().getId()).getId()));
		}
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int idComp) {
		ModelAndView res;
		res = this.createEditModelAndView(this.reportService.createReport(idComp));
		res.addObject("requestURI", "report/referee/edit.do");
		res.addObject("idComp", idComp);
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@Valid final Report r, final BindingResult bind, @RequestParam final int idComp) {
		ModelAndView result;
		if (bind.hasErrors())
			result = this.createEditModelAndView(r);
		else
			try {
				this.reportService.save(r, idComp);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(r, "report.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int idRep) {
		ModelAndView result;
		Report r;
		r = this.reportService.findOne(idRep);
		Assert.notNull(r);
		result = this.createEditModelAndView(r);
		result.addObject("idComp", this.reportService.findOne(idRep).getComplaint().getId());
		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int idRep) {
		ModelAndView result;

		try {
			this.reportService.delete(idRep);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(this.reportService.findOne(idRep), "report.commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Report r) {
		ModelAndView result;
		result = this.createEditModelAndView(r, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Report r, final String message) {
		ModelAndView result;
		result = new ModelAndView("report/edit");
		result.addObject("report", r);
		result.addObject("message", message);
		return result;
	}

}
