package ga.dqrjz.marking.service;


import ga.dqrjz.marking.pojo.Document;

import java.util.List;

public interface DocumentService {
	Document selectDocument(Document document);
	
	List<Document> selectDocuments(Document document);
	
	void updateDocument(Document document);
	
	void updateDocuments(List<Document> documentList);
}
