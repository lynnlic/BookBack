package com.cqut.util;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.minidev.json.JSONObject;

import com.cqut.util.Jwt;
import com.cqut.util.TokenState;
/**
 * toekn校验过滤器，所有的API接口请求都要经过该过滤器(除了登陆接口)
 * @author running@vip.163.com
 *
 */
public class CheckTokenFilter  implements Filter {


	@Override
	public void doFilter(ServletRequest argo, ServletResponse arg1,
			FilterChain chain ) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) argo;
		HttpServletResponse response=(HttpServletResponse) arg1;
//		response.setHeader("Access-Control-Allow-Origin", "*");
		if(request.getRequestURI().endsWith("/servlet/login")||
				request.getRequestURI().endsWith("/servlet/access")){
			//登陆接口不校验token，直接放行
			chain.doFilter(request, response);
			return;
		}
		//其他API接口一律校验token
		System.out.println("开始校验token");
		//从请求头中获取token
//		String token=request.getHeader("token");
		String auth = request.getHeader("Authorization");
		String token = "";
		if(auth != null && auth.length() > 7) {
			String headStr = auth.substring(0,6).toLowerCase();
			if(headStr.equals("bearer")) {
				token = auth.substring(7,auth.length());
			}
		}
		Map<String, Object> resultMap=Jwt.validToken(token);
		TokenState state=TokenState.getTokenState((String)resultMap.get("state"));
		switch (state) {
		case VALID:
			//放行
			//更新token
			token = Jwt.refreshToken(token);
			response.setHeader("Refresh-Token", token);
			chain.doFilter(request, response);
			break;
		case EXPIRED:
		case INVALID:
			System.out.println("无效token");
			//token过期或者无效，则输出错误信息返回给ajax
			JSONObject outputMSg=new JSONObject();
			outputMSg.put("success", false);
			outputMSg.put("msg", "您的token不合法或者过期了，请重新登陆");
			output(outputMSg.toJSONString(), response);
			break;
		}
		
		
	}
	
	
	public void output(String jsonStr,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8;");
		PrintWriter out = response.getWriter();
//		out.println();
		out.write(jsonStr);
		out.flush();
		out.close();
		
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("token过滤器初始化了");
	}

	@Override
	public void destroy() {
		
	}

}
