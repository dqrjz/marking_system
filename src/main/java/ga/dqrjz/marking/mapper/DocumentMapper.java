package ga.dqrjz.marking.mapper;

import com.github.abel533.mapper.Mapper;
import ga.dqrjz.marking.pojo.Document;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DocumentMapper extends Mapper<Document> {
	List<Document> selectAllDocuments();
	
//	List<Document> selectMarkedDocuments();
//
//	List<Document> selectMarkedDocumentsByUserId(@Param("userId") Long userId);
//
//	List<Document> selectUnmarkedDocuments();
//
//	List<Document> selectUnmarkedDocumentsByUserId(@Param("userId") Long userId);
//
//	List<Document> selectAssignedDocuments();
//
//	List<Document> selectAssignedDocumentsByUserId(@Param("userId") Long userId);
	
	List<Document> selectMarkedDocumentIds();
	
	List<Document> selectUnmarkedDocumentIds();
	
	List<Document> selectMarkedDocumentIdsByUserId(@Param("userId") Long userId);
	
	List<Document> selectUnmarkedDocumentIdsByUserId(@Param("userId") Long userId);
	
//	List<Document> selectDocumentsByUserId(@Param("userId") Long userId);
}
