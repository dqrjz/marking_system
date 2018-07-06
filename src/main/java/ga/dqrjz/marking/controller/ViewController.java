package ga.dqrjz.marking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {
	@GetMapping("/login")
	public String login() {
		return "forward:login.html";
	}
	
	@GetMapping("/admin")
	public String adminMain() {
		return "forward:/admin/main.html";
	}
	
	@GetMapping("/user/{username}")
	public String userMain(@PathVariable("username") String username) {
		System.out.println("/user/{username}: " + username);
		return "forward:/standard/main.html";
	}
	
}
