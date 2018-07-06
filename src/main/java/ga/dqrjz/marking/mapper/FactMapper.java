package ga.dqrjz.marking.mapper;

import ga.dqrjz.marking.pojo.Fact;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface FactMapper extends Mapper<Fact> {
	List<Fact> selectFactsByDocumentId(@Param("documentId") Long documentId);
	
	List<String> selectFactMarkingStatusListByDocumentId(Long documentId);
}
