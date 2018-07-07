package ga.dqrjz.marking.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "mark")
public class Mark {
	@Id
	private Long mid;
	private Integer value; // -1=unmarked, 0=unrelated, 1=related
	private Long factId;
	private Long evidenceId;
	private Long documentId;
	private Integer rowIndex; // should start from 1
	private Integer colIndex; // should start from 1
	
	public Mark() {
	}
	
	public Mark(Long mid, Integer value, Long factId, Long evidenceId, Long documentId, Integer rowIndex, Integer
			colIndex) {
		this.mid = mid;
		this.value = value;
		this.factId = factId;
		this.evidenceId = evidenceId;
		this.documentId = documentId;
		this.rowIndex = rowIndex;
		this.colIndex = colIndex;
	}
	
	@Override
	public String toString() {
		return "Mark{" +
				"mid=" + mid +
				", value=" + value +
				", factId=" + factId +
				", evidenceId=" + evidenceId +
				", documentId=" + documentId +
				", rowIndex=" + rowIndex +
				", colIndex=" + colIndex +
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
	
	public Long getEvidenceId() {
		return evidenceId;
	}
	
	public void setEvidenceId(Long evidenceId) {
		this.evidenceId = evidenceId;
	}
	
	public Long getDocumentId() {
		return documentId;
	}
	
	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}
	
	public Integer getRowIndex() {
		return rowIndex;
	}
	
	public void setRowIndex(Integer rowIndex) {
		this.rowIndex = rowIndex;
	}
	
	public Integer getColIndex() {
		return colIndex;
	}
	
	public void setColIndex(Integer colIndex) {
		this.colIndex = colIndex;
	}
}
