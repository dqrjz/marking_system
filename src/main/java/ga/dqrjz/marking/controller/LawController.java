package ga.dqrjz.marking.controller;

import ga.dqrjz.marking.pojo.Law;
import ga.dqrjz.marking.service.LawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/law")
@RestController
public class LawController {
	@Autowired
	private LawService lawService;
	
	@GetMapping(value = "laws")
	public List<Law> selectLaws(Law law) {
		return lawService.selectLaws(law);
	}
}
