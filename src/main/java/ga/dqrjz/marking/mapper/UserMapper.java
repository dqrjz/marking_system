package ga.dqrjz.marking.mapper;

import ga.dqrjz.marking.pojo.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
	List<User> selectAllStandardUsernames();
}
