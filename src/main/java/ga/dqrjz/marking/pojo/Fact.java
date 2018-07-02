package ga.dqrjz.marking.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Table(name = "fact")
public class Fact {
	@Id
	private Long fid; // auto_increment
	private String content;
	private Long documentId;
	private String factMarkingStatus; // N=unmarked, Y=marked
	@Transient
	private List<Mark> markList; // length = number of laws
	
	public Fact() {
	}
	
	public Fact(Long fid, String content, Long documentId, String factMarkingStatus) {
		this.fid = fid;
		this.content = content;
		this.documentId = documentId;
		this.factMarkingStatus = factMarkingStatus;
	}
	
	public Fact(Long fid, String content, Long documentId, String factMarkingStatus, List<Mark> markList) {
		this.fid = fid;
		this.content = content;
		this.documentId = documentId;
		this.factMarkingStatus = factMarkingStatus;
		this.markList = markList;
	}
	
	@Override
	public String toString() {
		return "Fact{" +
				"fid=" + fid +
				", content='" + content + '\'' +
				", documentId=" + documentId +
				", factMarkingStatus='" + factMarkingStatus + '\'' +
				", markList=" + markList +
				'}';
	}
	
	public Long getFid() {
		return fid;
	}
	
	public void setFid(Long fid) {
		this.fid = fid;
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
	
	public String getFactMarkingStatus() {
		return factMarkingStatus;
	}
	
	public void setFactMarkingStatus(String factMarkingStatus) {
		this.factMarkingStatus = factMarkingStatus;
	}
	
	public List<Mark> getMarkList() {
		return markList;
	}
	
	public void setMarkList(List<Mark> markList) {
		this.markList = markList;
	}
}
