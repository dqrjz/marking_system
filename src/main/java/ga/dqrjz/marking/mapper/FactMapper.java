package ga.dqrjz.marking.mapper;

import com.github.abel533.mapper.Mapper;
import ga.dqrjz.marking.pojo.Fact;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FactMapper extends Mapper<Fact> {
	List<Fact> selectFactsByDocumentId(@Param("documentId") Long documentId);
	
	List<String> selectFactMarkingStatusListByDocumentId(Long documentId);
}
