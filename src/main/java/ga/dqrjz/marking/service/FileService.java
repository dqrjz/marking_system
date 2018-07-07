package ga.dqrjz.marking.service;

import ga.dqrjz.marking.pojo.Mark;

import java.io.File;

public interface FileService {
	void importFile(File file);
	
	void updateFileMarks(Mark mark);
}
