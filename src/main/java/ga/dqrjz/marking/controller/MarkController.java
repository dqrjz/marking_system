package ga.dqrjz.marking.controller;

import ga.dqrjz.marking.pojo.MarkVO;
import ga.dqrjz.marking.pojo.ResultInfo;
import ga.dqrjz.marking.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("mark")
@Controller
public class MarkController {
	@Autowired
	private MarkService markService;
	
	@RequestMapping(value = "updateMarks")
	@ResponseBody
	public ResultInfo updateMarks(MarkVO markVO) {
		System.out.println(markVO);
		markService.updateMarks(markVO.getMarkList());
		return new ResultInfo(true);
	}
}
