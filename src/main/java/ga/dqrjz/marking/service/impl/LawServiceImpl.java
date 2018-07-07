package ga.dqrjz.marking.service.impl;

import ga.dqrjz.marking.mapper.LawMapper;
import ga.dqrjz.marking.pojo.Law;
import ga.dqrjz.marking.service.LawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service("lawService")
public class LawServiceImpl implements LawService {
	@Autowired
	private LawMapper lawMapper;
	
	@Override
	public List<Law> selectLaws(Law law) {
		return lawMapper.select(law);
	}

}
