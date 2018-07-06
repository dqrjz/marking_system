package ga.dqrjz.marking.controller;

import ga.dqrjz.marking.pojo.User;
import ga.dqrjz.marking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/admin/user")
@RestController
public class AdminUserController {
	@Autowired
	private UserService userService;
	
	//获取所有standard user
	@GetMapping(value = "usernames")
	public List<User> selectAllStandardUsernames() {
		return userService.selectAllStandardUsernames();
	}
}
