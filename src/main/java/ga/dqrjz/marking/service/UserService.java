package ga.dqrjz.marking.service;

import ga.dqrjz.marking.exception.PasswordErrorException;
import ga.dqrjz.marking.exception.UsernameNullException;
import ga.dqrjz.marking.exception.UserNotFoundException;
import ga.dqrjz.marking.pojo.User;

import java.util.List;

public interface UserService {
	User login(User user) throws UsernameNullException, UserNotFoundException, PasswordErrorException;
	
	List<User> selectAllStandardUsernames();
}
