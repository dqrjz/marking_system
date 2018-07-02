package ga.dqrjz.marking.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "law")
public class Law {
	@Id
	private Long lid; //auto_increment
	private String name;
	private String content;
	private Long documentId;
	
	public Law() {
	
	}
	
	public Law(Long id, String name, String content, Long documentId) {
		
		this.lid = id;
		this.name = name;
		this.content = content;
		this.documentId = documentId;
	}
	
	
	@Override
	public String toString() {
		return "Law{" +
				"lid=" + lid +
				", name='" + name + '\'' +
				", content='" + content + '\'' +
				", documentId=" + documentId +
				'}';
	}
	
	public Long getLid() {
		return lid;
	}
	
	public void setLid(Long lid) {
		this.lid = lid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
