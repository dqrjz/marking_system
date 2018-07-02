package ga.dqrjz.marking.pojo;

import java.util.List;

public class DocumentVO {
	private List<Document> documentList;
	
	public List<Document> getDocumentList() {
		return documentList;
	}
	
	public void setDocumentList(List<Document> documentList) {
		this.documentList = documentList;
	}
	
	@Override
	public String toString() {
		return "DocumentVO{" +
				"documentList=" + documentList +
				'}';
	}
}
