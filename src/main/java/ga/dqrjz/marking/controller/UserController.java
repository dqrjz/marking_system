package ga.dqrjz.marking.controller;

import ga.dqrjz.marking.exception.PasswordErrorException;
import ga.dqrjz.marking.exception.UserNotFoundException;
import ga.dqrjz.marking.exception.UsernameNullException;
import ga.dqrjz.marking.pojo.ResultInfo;
import ga.dqrjz.marking.pojo.User;
import ga.dqrjz.marking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("user")
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	//处理用户登录请求
	@PostMapping(value = "getLoginUserData")
	@ResponseBody
	public ResultInfo getLoginUserData(HttpServletRequest request) {
		//实例返回结果对象
		ResultInfo resultInfo;
		//从session里面获取登录的用户数据
		User user = (User) request.getSession().getAttribute("loginUser");
		if (user == null) {
			//说明用户没有登录
			resultInfo = new ResultInfo(false, null, null);
		} else {
			//说明用户登录
			resultInfo = new ResultInfo(true, user, null);
		}
		return resultInfo;
	}
	
	@PostMapping(value = "login")
	@ResponseBody
	public ResultInfo login(HttpServletRequest request, User user) {
		//实例返回结果对象
		ResultInfo resultInfo = null;
		System.out.println(user);
		try {
			//加密密码，之后再去数据库对比
			//todo
//			user.setPassword(Md5Util.encodeByMd5(user.getPassword()));
			
			//调用业务进行登录
			User loginUser = userService.login(user);
			//登录成功，返回成功信息
			if (loginUser != null) {
				//登录成功，将登录用户数据写入session
				request.getSession().setAttribute("loginUser", loginUser);
				request.getSession().setAttribute("privilege", loginUser.getPrivilege());
				resultInfo = new ResultInfo(true,loginUser.getPrivilege());
			}
		} catch (PasswordErrorException | UserNotFoundException | UsernameNullException e) {
			e.printStackTrace();//让开发人员看
			resultInfo = new ResultInfo(false, null, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo = new ResultInfo(false, null, "服务器忙");
		}
		System.out.println(resultInfo);
		return resultInfo;
	}
	
	//处理用户注销请求
	@PostMapping(value = "logout")
	@ResponseBody
	public ResultInfo logout(HttpServletRequest request) {
		ResultInfo resultInfo = null;
		try {
			//销毁session
			request.getSession().invalidate();//销毁session
			resultInfo = new ResultInfo(true, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo = new ResultInfo(false, null, e.getMessage());
		}
		return resultInfo;
	}
}
