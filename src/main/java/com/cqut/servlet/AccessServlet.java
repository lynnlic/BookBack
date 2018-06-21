package com.cqut.servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqut.util.Jwt;
import com.cqut.util.TokenState;

import net.minidev.json.JSONObject;

public class AccessServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AccessServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String auth = request.getHeader("Authorization");
		JSONObject jsonObject = new JSONObject();
		String refreshToken = null;
		System.out.println("auth " + auth);
		if(auth != null && auth.length() > 7) {
			String headStr = auth.substring(0,6).toLowerCase();
			if(headStr.equals("bearer")) {
				String token = auth.substring(7,auth.length());
				Map<String, Object> resultMap=Jwt.validToken(token);
				String state = (String)resultMap.get("state");
				jsonObject.put("state", state);
				
				//如果token验证有效则刷新token
				if(resultMap.get("state").equals("VALID")) {
					refreshToken = Jwt.refreshToken(token);
					response.setHeader("Refresh-Token", refreshToken);
				}
				
			}else {
				jsonObject.put("state", "ERROR");
			}
		}else {
			jsonObject.put("state", "ERROR");
		}
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("text/plain; charset=utf-8;"); 
	    String jsonStr = jsonObject.toJSONString();
	    System.out.println("jsonStr " + jsonStr);
	    output(jsonStr, response);
	}
	
	public void output(String jsonStr,HttpServletResponse response) throws IOException{
		
		PrintWriter out = response.getWriter();
		out.println(jsonStr);
		out.flush();
		out.close();
	}


	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
