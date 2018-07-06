package ga.dqrjz.marking.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "evidence")
public class Evidence {
	@Id
	private Long eid;
	private String content;
	private Long documentId;
	
	public Evidence() {
	}
	
	public Evidence(Long eid, String content, Long documentId) {
		this.eid = eid;
		this.content = content;
		this.documentId = documentId;
	}
	
	@Override
	public String toString() {
		return "Evidence{" +
				"eid=" + eid +
				", content='" + content + '\'' +
				", documentId=" + documentId +
				'}';
	}
	
	public Long getEid() {
		return eid;
	}
	
	public void setEid(Long eid) {
		this.eid = eid;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Long getDocumentId() {
		return documentId;
	}
	
	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}
}
