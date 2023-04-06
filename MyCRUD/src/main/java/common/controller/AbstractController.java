package common.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractController implements InterCommand {

	/*
	   === 다음의 나오는 것은 우리끼리한 약속이다 ===
	   
	   * view 단 페이지(.jsp)로 이동시 
	   	forward 방법(dispatcher)으로 하고자 
	   	자식 클래스에서는 부모클래스에서 생성해둔 메소드 호출 시 아래와 같이 하면 된다.
	   	
	   	super.setRedirect(false);
	   	super.setViewPage("/WEB-INF/SDKAS/LD");
	   	
	   
	   * url 주소를 변경하여 페이지 이동시 sendRedirect 방법으로 해야 하는데
	   자식 클래스에서는 부모클래스에서 생성해둔 메소드 호출 시 아래와 같이 하면 된다.
	   	super.setRedirect(true);
	   	super.setViewPage("registerMember.up");
	   
	*/
	
	private boolean isRedirect = false;
	// isRedirect 변수의 값이 false 이라면 view 단 페이지(.jsp)로 forward(dispatcher)하겠다는 말이다.
	// isRedirect 변수의 값이 true라면 view 단 페이지(.jsp)로 sendRedirect하겠다는 말이다.
	
	private String viewPage;
	// viewPage 는 isRedirect 값이 false이라면 view 단 페이지(.jsp) 경로명이 되고,
	// viewPage 는 isRedirect 값이 true라면 이동해야할 url 주소가 된다.
	
	
	
	public abstract void execute(HttpServletRequest request, HttpServletResponse response);



	public boolean isRedirect() {
		return isRedirect;
	}



	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}



	public String getViewPage() {
		return viewPage;
	}



	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}
	
}
