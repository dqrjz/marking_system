package ga.dqrjz.marking.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Table(name = "user")
public class User {
	@Id
	private Long uid;
	private String username;
	@JsonIgnore
	private String password;
	private String privilege;
	@Transient
	private List<Document> markedDocumentList;
	@Transient
	private List<Document> unmarkedDocumentList;
	
	public User() {
	}
	
	public User(Long uid, String username, String password, String privilege, List<Document> markedDocumentList,
	            List<Document> unmarkedDocumentList) {
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.privilege = privilege;
		this.markedDocumentList = markedDocumentList;
		this.unmarkedDocumentList = unmarkedDocumentList;
	}
	
	@Override
	public String toString() {
		return "User{" +
				"uid=" + uid +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", privilege='" + privilege + '\'' +
				", markedDocumentList=" + markedDocumentList +
				", unmarkedDocumentList=" + unmarkedDocumentList +
				'}';
	}
	
	public Long getUid() {
		return uid;
	}
	
	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPrivilege() {
		return privilege;
	}
	
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	
	public List<Document> getMarkedDocumentList() {
		return markedDocumentList;
	}
	
	public void setMarkedDocumentList(List<Document> markedDocumentList) {
		this.markedDocumentList = markedDocumentList;
	}
	
	public List<Document> getUnmarkedDocumentList() {
		return unmarkedDocumentList;
	}
	
	public void setUnmarkedDocumentList(List<Document> unmarkedDocumentList) {
		this.unmarkedDocumentList = unmarkedDocumentList;
	}
}
