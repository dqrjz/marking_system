package ga.dqrjz.marking.service.impl;

import ga.dqrjz.marking.exception.PasswordErrorException;
import ga.dqrjz.marking.exception.UserNotFoundException;
import ga.dqrjz.marking.exception.UsernameNullException;
import ga.dqrjz.marking.mapper.UserMapper;
import ga.dqrjz.marking.pojo.User;
import ga.dqrjz.marking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User login(User user) throws UsernameNullException, UserNotFoundException, PasswordErrorException {
		String username = user.getUsername();
		String password = user.getPassword();
		//数据校验
		if (username == null || username.equalsIgnoreCase("")) {
			throw new UsernameNullException("用户名不能为空");
		}
		User selectUser = new User();
		selectUser.setUsername(username);
		
		//判断用户名是否存在
		User loginUser = userMapper.selectOne(selectUser);
		if (loginUser == null) {
			throw new UserNotFoundException("用户名不存在");
		}
		//判断密码是否正确
		if (!loginUser.getPassword().equals(password)) {
			throw new PasswordErrorException("密码错误");
		}
		System.out.println("loginUser=" + loginUser);
		return loginUser;
	}
	
	@Override
	public List<User> selectAllStandardUsernames() {
		return userMapper.selectAllStandardUsernames();
	}
	
}
