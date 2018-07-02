package ga.dqrjz.marking.mapper;

import com.github.abel533.mapper.Mapper;
import ga.dqrjz.marking.pojo.Law;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LawMapper extends Mapper<Law> {
//	List<Law> selectLawsByDocumentId(@Param("documentId") Long documentId);
}
