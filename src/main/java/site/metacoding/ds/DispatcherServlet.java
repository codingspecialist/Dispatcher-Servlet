package site.metacoding.ds;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doProcess 요청됨");
		String httpMethod = req.getMethod();
		//System.out.println(httpMethod);
		String identifier = req.getRequestURI();
		//System.out.println(identifier);
		
		// 공통 로직 처리
		UserController c = new UserController();
		
		Method[] methodList = c.getClass().getDeclaredMethods();
		for (Method method : methodList) {
			//System.out.println(method.getName());
			Annotation annotation = 
					method.getDeclaredAnnotation(RequestMapping.class);
			RequestMapping requestMapping = (RequestMapping) annotation;
			
			
			
			try {
				Parameter[] params = method.getParameters();
				Object[] q = new Object[params.length];
		
				for (int i=0; i<params.length; i++) {
					Class<?> cls = params[i].getType();
					if(cls == HttpServletRequest.class) {
						q[i] = req;
						System.out.println(method.getName() +" : 리케스트 주입됨");
					}
					
					if(cls == HttpServletResponse.class) {
						q[i] = resp;
						System.out.println(method.getName() +" : 리스펀스 주입됨");
					}
					
					if(cls == HttpSession.class) {
						q[i] = req.getSession();
					}
				}
				
				//System.out.println(requestMapping.value());
				if(identifier.equals(requestMapping.value())) {
					method.invoke(c, q);
				}
			} catch (Exception e) {
				System.out.println(method.getName()+"은 어노테이션이 없습니다.");
			}
			
		}
	}
}
