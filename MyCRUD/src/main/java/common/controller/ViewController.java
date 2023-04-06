package common.controller;

import java.io.*;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



@WebServlet(
		urlPatterns = { "*.book" }, 
		initParams = { 
				@WebInitParam(name = "propertyConfig", value = "properties가 있는 경로", description = "*.book")
		})
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Map<String, Object> cmdMap = new HashMap<>();
	
	public void inti(ServletConfig config) throws ServletException {
		
		FileInputStream fis = null;
		
		String props = config.getInitParameter("propertyConfig");
		
		try {
			fis = new FileInputStream(props);
			
			Properties pr = new Properties();
			
			pr.load(fis);
			
			Enumeration<Object> en = pr.keys();
		}
		
		catch (FileNotFoundException e) {
			System.out.println(">>> C:/NCS/workspace(jsp)/MyMVC/src/main/webapp/WEB-INF/Command.properties 파일이 없습니다 <<<");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			System.out.println("문자열로 명명되어진 클래스가 존재하지 않습니다.");
			e.printStackTrace();
		} catch (Exception e) {
			
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		
		String key = uri.substring(request.getContextPath().length());
		
		AbstractController action = (AbstractController)cmdMap.get(key);
		
		if(action == null) {
			// url과 매핑된 클래스가 없으면 예외처리하는 곳
		}
		else {
			request.setCharacterEncoding("UTF-8");
			
			action.execute(request, response);
			boolean bool = action.isRedirect();
			String viewPage = action.getViewPage();
			
			if(!bool) {
				// forward를 하는 경우
				if(viewPage != null) {
					RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
					dispatcher.forward(request, response);
				}
			}
			else {
				if(viewPage != null) {
					response.sendRedirect(viewPage);
				}
			}
		}

	} 
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}