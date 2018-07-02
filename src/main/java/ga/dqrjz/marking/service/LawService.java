package ga.dqrjz.marking.service;

import ga.dqrjz.marking.pojo.Law;

import java.util.List;

public interface LawService {
//	List<Law> selectLawsByDocumentId(int documentId);
	List<Law> selectLaws(Law law);
}
