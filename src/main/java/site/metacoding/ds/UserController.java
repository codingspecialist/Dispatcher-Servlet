package site.metacoding.ds;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserController {
	
	@RequestMapping("/join")
	public void join(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println(req.getRequestURL()+" : ");
		System.out.println("join 호출됨");
	}
	
	@RequestMapping("/login")
	public void login() {
		System.out.println("login 호출됨");
	}
	
	@RequestMapping("/joinForm")
	public void joinForm() {
		System.out.println("joinForm 호출됨");
	}
}
