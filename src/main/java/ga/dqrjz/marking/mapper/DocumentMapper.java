package ga.dqrjz.marking.mapper;

import ga.dqrjz.marking.pojo.Document;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DocumentMapper extends Mapper<Document> {
	
	List<Document> selectMarkedDocumentIds();
	
	List<Document> selectUnmarkedDocumentIds();
	
	List<Document> selectMarkedDocumentIdsByUserId(@Param("userId") Long userId);
	
	List<Document> selectUnmarkedDocumentIdsByUserId(@Param("userId") Long userId);
}
