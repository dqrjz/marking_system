package ga.dqrjz.marking.controller;

import ga.dqrjz.marking.pojo.User;
import ga.dqrjz.marking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("admin/user")
@Controller
public class AdminUserController {
	@Autowired
	private UserService userService;
	
	//获取所有standard user
	@PostMapping(value = "selectAllStandardUsernames")
	@ResponseBody
	public List<User> selectAllStandardUsernames() {
		return userService.selectAllStandardUsernames();
	}
}
