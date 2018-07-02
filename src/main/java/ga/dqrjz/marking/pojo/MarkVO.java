package ga.dqrjz.marking.pojo;

import java.util.List;

public class MarkVO {
	private List<Mark> markList;
	
	public List<Mark> getMarkList() {
		return markList;
	}
	
	public void setMarkList(List<Mark> markList) {
		this.markList = markList;
	}
	
	@Override
	public String toString() {
		return "MarkVO{" +
				"markList=" + markList +
				'}';
	}
}
