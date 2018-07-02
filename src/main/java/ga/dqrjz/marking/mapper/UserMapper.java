package ga.dqrjz.marking.mapper;

import com.github.abel533.mapper.Mapper;
import ga.dqrjz.marking.pojo.User;

import java.util.List;

public interface UserMapper extends Mapper<User> {
	List<User> selectAllStandardUsernames();
}
