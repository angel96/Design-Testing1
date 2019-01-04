
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

import services.ReportService;
import utilities.Utiles;
import domain.Report;

@Controller
@RequestMapping("/report/referee")
public class ReportController extends AbstractController {

	@Autowired
	private ReportService	reportService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		res = new ModelAndView("report/edit");
		res.addObject("report", Utiles.createReport());
		res.addObject("requestURI", "report/referee/create.do");
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@Valid final Report r, final BindingResult bind) {
		ModelAndView result;
		if (bind.hasErrors())
			result = this.createEditModelAndView(r);
		else
			try {
				this.reportService.save(r);
				result = new ModelAndView("redirect:welcome.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(r, "report.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {
		ModelAndView result;
		Report r;
		r = this.reportService.findOne(id);
		Assert.notNull(r);
		result = this.createEditModelAndView(r);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Report r) {
		ModelAndView result;

		try {
			this.reportService.delete(r);
			result = new ModelAndView("redirect:welcome.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(r, "report.commit.error");
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
