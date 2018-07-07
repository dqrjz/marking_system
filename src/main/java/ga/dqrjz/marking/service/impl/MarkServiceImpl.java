package ga.dqrjz.marking.service.impl;

import ga.dqrjz.marking.mapper.DocumentMapper;
import ga.dqrjz.marking.mapper.FactMapper;
import ga.dqrjz.marking.mapper.MarkMapper;
import ga.dqrjz.marking.pojo.Document;
import ga.dqrjz.marking.pojo.Fact;
import ga.dqrjz.marking.pojo.Mark;
import ga.dqrjz.marking.service.FileService;
import ga.dqrjz.marking.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service("markService")
public class MarkServiceImpl implements MarkService {
	@Autowired
	private MarkMapper markMapper;
	@Autowired
	private FactMapper factMapper;
	@Autowired
	private DocumentMapper documentMapper;
	@Autowired
	private FileService fileService;
	
	@Override
	public void updateMarks(List<Mark> markList) {
		Set<Long> factIdSet = new HashSet<>();
		Set<Long> documentIdSet = new HashSet<>();
		for (Mark mark : markList) {
			factIdSet.add(mark.getFactId());
			documentIdSet.add(mark.getDocumentId());
			fileService.updateFileMarks(mark);
			mark.setFactId(null);
			mark.setDocumentId(null);
			markMapper.updateByPrimaryKeySelective(mark);
		}
		// 更新FactMarkingStatus
		Fact fact = new Fact();
		for (Long factId : factIdSet) {
			fact.setFid(factId);
			List<Long> markValueList = markMapper.selectMarkValueListByFactId(factId);
			if (markValueList.contains(-1L)) {
				fact.setFactMarkingStatus("N");
			} else {
				fact.setFactMarkingStatus("Y");
			}
			factMapper.updateByPrimaryKeySelective(fact);
		}
		// 更新DocumentMarkingStatus
		Document document = new Document();
		for (Long documentId : documentIdSet) {
			document.setDid(documentId);
			List<String> factMarkingStatusList = factMapper.selectFactMarkingStatusListByDocumentId(documentId);
			if (factMarkingStatusList.contains("N")) {
				document.setDocumentMarkingStatus("N");
			} else {
				document.setDocumentMarkingStatus("Y");
			}
			documentMapper.updateByPrimaryKeySelective(document);
		}
	}
}
