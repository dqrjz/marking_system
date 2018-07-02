package ga.dqrjz.marking.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Table(name = "document")
public class Document {
	@Id
	private Long did;
	private String filenameXml;
	private String filenameXls;
	private String documentAssignmentStatus; // N=unassigned, Y=assigned
	private Long userId; // -1=unassigned
	private String documentMarkingStatus; // N=unmarked, Y=marked
	private String caseName;
	private String caseNumber;
	private String caseReason;
	private String caseCategory;
	@Transient
	private List<Fact> factList; // markList放在fact中
	//	private List<Mark> markList;
	@Transient
	private List<Law> lawList;
	@Transient
	private User user;
	
	public Document() {
	}
	
	public Document(Long did, String filenameXml, String filenameXls, String documentAssignmentStatus, Long userId,
	                String documentMarkingStatus, String caseName, String caseNumber, String caseReason, String
			                caseCategory) {
		this.did = did;
		this.filenameXml = filenameXml;
		this.filenameXls = filenameXls;
		this.documentAssignmentStatus = documentAssignmentStatus;
		this.userId = userId;
		this.documentMarkingStatus = documentMarkingStatus;
		this.caseName = caseName;
		this.caseNumber = caseNumber;
		this.caseReason = caseReason;
		this.caseCategory = caseCategory;
	}
	
	public Document(Long did, String filenameXml, String filenameXls, String documentAssignmentStatus, Long userId,
	                String documentMarkingStatus, String caseName, String caseNumber, String caseReason, String
			                caseCategory, List<Fact> factList, List<Law> lawList, User user) {
		this.did = did;
		this.filenameXml = filenameXml;
		this.filenameXls = filenameXls;
		this.documentAssignmentStatus = documentAssignmentStatus;
		this.userId = userId;
		this.documentMarkingStatus = documentMarkingStatus;
		this.caseName = caseName;
		this.caseNumber = caseNumber;
		this.caseReason = caseReason;
		this.caseCategory = caseCategory;
		this.factList = factList;
		this.lawList = lawList;
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "Document{" +
				"did=" + did +
				", filenameXml='" + filenameXml + '\'' +
				", filenameXls='" + filenameXls + '\'' +
				", documentAssignmentStatus='" + documentAssignmentStatus + '\'' +
				", userId=" + userId +
				", documentMarkingStatus='" + documentMarkingStatus + '\'' +
				", caseName='" + caseName + '\'' +
				", caseNumber='" + caseNumber + '\'' +
				", caseReason='" + caseReason + '\'' +
				", caseCategory='" + caseCategory + '\'' +
				", factList=" + factList +
				", lawList=" + lawList +
				", user=" + user +
				'}';
	}
	
	public Long getDid() {
		return did;
	}
	
	public void setDid(Long did) {
		this.did = did;
	}
	
	public String getFilenameXml() {
		return filenameXml;
	}
	
	public void setFilenameXml(String filenameXml) {
		this.filenameXml = filenameXml;
	}
	
	public String getFilenameXls() {
		return filenameXls;
	}
	
	public void setFilenameXls(String filenameXls) {
		this.filenameXls = filenameXls;
	}
	
	public String getDocumentAssignmentStatus() {
		return documentAssignmentStatus;
	}
	
	public void setDocumentAssignmentStatus(String documentAssignmentStatus) {
		this.documentAssignmentStatus = documentAssignmentStatus;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getDocumentMarkingStatus() {
		return documentMarkingStatus;
	}
	
	public void setDocumentMarkingStatus(String documentMarkingStatus) {
		this.documentMarkingStatus = documentMarkingStatus;
	}
	
	public String getCaseName() {
		return caseName;
	}
	
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	
	public String getCaseNumber() {
		return caseNumber;
	}
	
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	
	public String getCaseReason() {
		return caseReason;
	}
	
	public void setCaseReason(String caseReason) {
		this.caseReason = caseReason;
	}
	
	public String getCaseCategory() {
		return caseCategory;
	}
	
	public void setCaseCategory(String caseCategory) {
		this.caseCategory = caseCategory;
	}
	
	public List<Fact> getFactList() {
		return factList;
	}
	
	public void setFactList(List<Fact> factList) {
		this.factList = factList;
	}
	
	public List<Law> getLawList() {
		return lawList;
	}
	
	public void setLawList(List<Law> lawList) {
		this.lawList = lawList;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
