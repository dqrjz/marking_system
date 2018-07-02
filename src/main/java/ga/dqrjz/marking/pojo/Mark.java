package ga.dqrjz.marking.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "mark")
public class Mark {
	@Id
	private Long mid;
	private Integer value; // -1=unmarked, 0=unrelated, 1=related
	private Long factId;
	private Long lawId;
	private Long documentId;
	
	public Mark() {
	}
	
	public Mark(Long mid, Integer value, Long factId, Long lawId, Long documentId) {
		this.mid = mid;
		this.value = value;
		this.factId = factId;
		this.lawId = lawId;
		this.documentId = documentId;
	}
	
	@Override
	public String toString() {
		return "Mark{" +
				"mid=" + mid +
				", value=" + value +
				", factId=" + factId +
				", lawId=" + lawId +
				", documentId=" + documentId +
				'}';
	}
	
	public Long getMid() {
		return mid;
	}
	
	public void setMid(Long mid) {
		this.mid = mid;
	}
	
	public Integer getValue() {
		return value;
	}
	
	public void setValue(Integer value) {
		this.value = value;
	}
	
	public Long getFactId() {
		return factId;
	}
	
	public void setFactId(Long factId) {
		this.factId = factId;
	}
	
	public Long getLawId() {
		return lawId;
	}
	
	public void setLawId(Long lawId) {
		this.lawId = lawId;
	}
	
	public Long getDocumentId() {
		return documentId;
	}
	
	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}
}
