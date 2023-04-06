package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface InterCommand {
	
	public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
