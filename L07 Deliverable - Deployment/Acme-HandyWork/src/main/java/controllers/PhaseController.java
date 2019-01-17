
package controllers;

import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.LoginService;
import security.UserAccount;
import services.FixUpTaskService;
import services.PhaseService;
import utilities.Utiles;
import domain.Actor;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Phase;

@Controller
@RequestMapping(value = {
	"/phase/handyworker", "/phase/customer"
})
public class PhaseController extends AbstractController {

	@Autowired
	private PhaseService		servicePhase;

	@Autowired
	private FixUpTaskService	servicefixuptask;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int fixuptask) {
		ModelAndView result;
		result = new ModelAndView("phase/list");
		final FixUpTask t = this.servicefixuptask.findOne(fixuptask);
		final UserAccount user = LoginService.getPrincipal();
		if (Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER)) {
			HandyWorker w;
			w = (HandyWorker) this.servicePhase.findByUserAccount(user.getId());
			final Collection<FixUpTask> fixuptaskHandy = this.servicefixuptask.getFixUpTasksByHandyWorker(w.getId());
			result.addObject("ownfixup", fixuptaskHandy.contains(t) && this.servicefixuptask.getAcceptedAppsByFixUp(fixuptask) != null);
		}

		result.addObject("phases", t.getPhases());
		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int fixuptask) {
		ModelAndView result;
		result = new ModelAndView("phase/edit");
		final Actor a = this.servicePhase.findByUserAccount(LoginService.getPrincipal().getId());

		if (Utiles.findAuthority(a.getAccount().getAuthorities(), Authority.HANDY_WORKER))
			result.addObject("requestURI", "phase/handyworker/edit.do?fixuptask=" + fixuptask);

		final FixUpTask t = this.servicefixuptask.findOne(fixuptask);
		result.addObject("start", t.getStart());
		result.addObject("end", t.getEnd());
		result.addObject("phase", this.servicePhase.createPhase());

		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {
		ModelAndView result;
		result = new ModelAndView("phase/edit");
		final Phase p = this.servicePhase.findOne(id);
		final FixUpTask t = this.servicePhase.findFixUpTaskByPhase(p);
		final SimpleDateFormat d = new SimpleDateFormat("yyyy/MM/dd");
		result.addObject("start", d.format(t.getStart()));
		result.addObject("end", d.format(t.getEnd()));
		result.addObject("phase", p);
		if (Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.CUSTOMER))
			result.addObject("view", true);
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView submit(@RequestParam final int fixuptask, @Valid final Phase phase, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = this.createEditModelAndView(phase);
			result.addObject("errors", binding.getAllErrors());
		} else
			try {
				final FixUpTask f = this.servicefixuptask.findOne(fixuptask);
				this.servicePhase.save(f, phase);
				result = new ModelAndView("redirect:list.do?fixuptask=" + f.getId());
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(phase, "phase.commit.error");
				result.addObject("errors", binding.getAllErrors());
				result.addObject("oops", oops.getMessage());
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView edit(final Phase phase) {
		ModelAndView result;

		try {
			final FixUpTask f = this.servicePhase.findFixUpTaskByPhase(phase);
			this.servicePhase.delete(phase);
			result = new ModelAndView("redirect:list.do?fixuptask=" + f.getId());
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(phase, "phase.commit.error");
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(final Phase phase) {
		ModelAndView result;
		result = this.createEditModelAndView(phase, null);
		return result;
	}
	protected ModelAndView createEditModelAndView(final Phase phase, final String message) {
		ModelAndView result;
		result = new ModelAndView("phase/edit");
		result.addObject("phase", phase);
		result.addObject("message", message);
		return result;
	}
}
