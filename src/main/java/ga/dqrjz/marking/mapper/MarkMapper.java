package ga.dqrjz.marking.mapper;

import ga.dqrjz.marking.pojo.Mark;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MarkMapper extends Mapper<Mark> {
	List<Long> selectMarkValueListByFactId(Long factId);
}
