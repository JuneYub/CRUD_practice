package test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractController;
import common.controller.InterCommand;

public class Test1Controller extends AbstractController {
	
	@Override
	public String toString() {
		return "@@@ 클래스 TestController 의 인스턴스 메소드 toString() 호출함 @@@";	
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("서버 테스트");
		request.setAttribute("message", "메시지 테스트");
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/test/test1.jsp");
	}

	
}
