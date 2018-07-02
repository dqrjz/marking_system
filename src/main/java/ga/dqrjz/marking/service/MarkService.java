package ga.dqrjz.marking.service;

import ga.dqrjz.marking.pojo.Mark;

import java.util.List;

public interface MarkService {
//	void updateMarksByFactId(Long factId, List<Integer> markValueList);
	
	void updateMarks(List<Mark> markList);
}
