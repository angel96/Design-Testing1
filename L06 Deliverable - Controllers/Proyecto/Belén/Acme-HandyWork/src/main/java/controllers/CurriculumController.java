
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.CurriculumService;
import services.EducationRecordService;
import services.EndorserRecordService;
import services.HandyWorkerService;
import services.MiscellaneousRecordService;
import services.ProfessionalRecordService;
import domain.EducationRecord;
import domain.EndorserRecord;
import domain.HandyWorker;
import domain.MiscellaneousRecord;
import domain.ProfessionalRecord;

@Controller
@RequestMapping("/curriculum/handyworker")
public class CurriculumController extends AbstractController {

	@Autowired
	private CurriculumService			curriculumService;

	@Autowired
	private HandyWorkerService			handyWorkerService;

	@Autowired
	private EducationRecordService		educationRecordService;

	@Autowired
	private ProfessionalRecordService	professionalRecordService;

	@Autowired
	private EndorserRecordService		endorserRecordService;

	@Autowired
	private MiscellaneousRecordService	miscellaneousRecordService;


	public CurriculumController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		final UserAccount user = LoginService.getPrincipal();
		final HandyWorker h;
		h = this.curriculumService.getHandyByUserAccount(user.getId());

		result = new ModelAndView("curriculum/list");

		System.out.println(h.getId());
		Collection<EducationRecord> education;
		education = this.educationRecordService.findEducationRecordByUserId(h.getId());
		result.addObject("educationrecord", education);
		System.out.println(education);

		Collection<ProfessionalRecord> professional;
		professional = this.professionalRecordService.findProfessionalRecordByUserId(h.getId());
		result.addObject("professionalrecord", professional);

		Collection<EndorserRecord> endorser;
		endorser = this.endorserRecordService.findEndorserRecordByUserId(h.getId());
		result.addObject("endorserrecord", endorser);

		Collection<MiscellaneousRecord> miscellaneous;
		miscellaneous = this.miscellaneousRecordService.findMiscellaneousRecordByUserId(h.getId());
		result.addObject("miscellaneousrecord", miscellaneous);

		result.addObject("handyworker", this.curriculumService.getHandyByUserAccount(user.getId()));
		result.addObject("linkedin", this.curriculumService.getLinkedInProfile(h.getId()));

		result.addObject("requestURI", "curriculum/handyworker/list.do");
		return result;
	}
}
