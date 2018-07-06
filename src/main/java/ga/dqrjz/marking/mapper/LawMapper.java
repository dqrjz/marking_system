package ga.dqrjz.marking.mapper;

import ga.dqrjz.marking.pojo.Law;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface LawMapper extends Mapper<Law> {
//	List<Law> selectLawsByDocumentId(@Param("documentId") Long documentId);
}
