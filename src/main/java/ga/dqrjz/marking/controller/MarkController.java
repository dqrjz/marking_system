package ga.dqrjz.marking.controller;

import ga.dqrjz.marking.pojo.MarkVO;
import ga.dqrjz.marking.pojo.ResultInfo;
import ga.dqrjz.marking.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/mark")
@RestController
public class MarkController {
	@Autowired
	private MarkService markService;
	
	@PatchMapping(value = "marks")
	public ResultInfo updateMarks(MarkVO markVO) {
		System.out.println(markVO);
		markService.updateMarks(markVO.getMarkList());
		return new ResultInfo(true);
	}
}
