package ga.dqrjz.marking.controller;

import ga.dqrjz.marking.pojo.Law;
import ga.dqrjz.marking.service.LawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("law")
@Controller
public class LawController {
	@Autowired
	private LawService lawService;
	
	@PostMapping(value = "selectLaws")
	@ResponseBody
	public List<Law> selectLaws(Law law) {
		return lawService.selectLaws(law);
	}
}
