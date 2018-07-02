package ga.dqrjz.marking.mapper;

import com.github.abel533.mapper.Mapper;
import ga.dqrjz.marking.pojo.Mark;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarkMapper extends Mapper<Mark> {
	List<Long> selectMarkValueListByFactId(Long factId);
}
