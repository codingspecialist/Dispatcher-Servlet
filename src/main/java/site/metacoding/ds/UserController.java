package site.metacoding.ds;

public class UserController {
	
	@RequestMapping("/join")
	public void join() {
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
