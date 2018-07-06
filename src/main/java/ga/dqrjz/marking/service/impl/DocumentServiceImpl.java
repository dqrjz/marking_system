package ga.dqrjz.marking.service.impl;

import ga.dqrjz.marking.mapper.DocumentMapper;
import ga.dqrjz.marking.mapper.FactMapper;
import ga.dqrjz.marking.mapper.LawMapper;
import ga.dqrjz.marking.pojo.Document;
import ga.dqrjz.marking.pojo.Law;
import ga.dqrjz.marking.pojo.User;
import ga.dqrjz.marking.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service("documentService")
public class DocumentServiceImpl implements DocumentService {
	@Autowired
	private DocumentMapper documentMapper;
	@Autowired
	private FactMapper factMapper;
	@Autowired
	private LawMapper lawMapper;
	
	@Override
	public Document selectDocument(Document document) {
		User loginUser = document.getUser();
		Long did = document.getDid();
		document = documentMapper.selectByPrimaryKey(did);
		if (Objects.equals("admin", loginUser.getPrivilege()) ||
				Objects.equals(document.getUserId(), loginUser.getUid())) {
			document.setFactList(factMapper.selectFactsByDocumentId(did));
			Law law = new Law();
			law.setDocumentId(did);
			document.setLawList(lawMapper.select(law));
			return document;
		} else {
			return null;
		}
	}
	
	@Override
	public List<Document> selectDocuments(Document document) {
		if (!Objects.equals(document.getUser().getPrivilege(), "admin")) {
			document.setUserId(document.getUser().getUid());
		}
		return documentMapper.select(document);
	}

	@Override
	public void updateDocument(Document document) {
		documentMapper.updateByPrimaryKeySelective(document);
	}
	
	@Override
	public void updateDocuments(List<Document> documentList) {
		for (Document document : documentList) {
			documentMapper.updateByPrimaryKeySelective(document);
		}
	}
}
