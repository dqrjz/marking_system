package ga.dqrjz.marking.controller;

import ga.dqrjz.marking.pojo.MarkVO;
import ga.dqrjz.marking.pojo.ResultInfo;
import ga.dqrjz.marking.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("mark")
@Controller
public class MarkController {
	@Autowired
	private MarkService markService;
	
//	@RequestMapping(value = "updateMarksByFactId")
//	@ResponseBody
//	public ResultInfo updateMarksByFactId(@RequestParam("factId") Long factId, HttpServletRequest request) {
//		String[] markValues = request.getParameter("markValues").split(",");
//		List<Integer> markValueList = new ArrayList<>();
//		for (String markValue : markValues) {
//			markValueList.add(Integer.parseInt(markValue));
//		}
////		System.out.println("fact=" + factId + ", markValueList=" + markValueList);
//		markService.updateMarksByFactId(factId, markValueList);
//		return new ResultInfo(true);
//	}
	
	@RequestMapping(value = "updateMarks")
	@ResponseBody
	public ResultInfo updateMarks(MarkVO markVO) {
		System.out.println(markVO);
		markService.updateMarks(markVO.getMarkList());
		return new ResultInfo(true);
	}
}
