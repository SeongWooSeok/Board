package filters.wrappers;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

/**
 * 공통 Request Filter Wrapper
 */
public class CommonRequestWrapper extends HttpServletRequestWrapper {

	public CommonRequestWrapper(HttpServletRequest request) throws UnsupportedEncodingException {
		super(request);
		
		/** 
		 * 요청 메서드 POST일때   
		 * 1. charset을 UTF-8로 고정
		 * 2. 요청 URL 세션에 저장  
		 */
		String method = request.getMethod().toUpperCase();
		if (method.equals("POST")) {
			request.setCharacterEncoding("UTF-8");
		}
		
		HttpSession session = request.getSession();
		String requestURL = request.getRequestURL().toString();
		session.setAttribute("requestURL", requestURL);
	}
}
