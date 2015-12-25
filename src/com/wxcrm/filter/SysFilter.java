package com.wxcrm.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.PatternMatchUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.wxcrm.sys.WcAdmin;
import com.wxcrm.util.SysConstant;



public class SysFilter extends OncePerRequestFilter
{
	private String except;
	
	private String[] excepts;
	//这个地方要提交上去
	public String getExcept() {
		return except;
	}

	public void setExcept(String except) {
		this.except = except;
		excepts = except.split(",");
		for( int i = 0; i < excepts.length; i++ )
		{
			excepts[i] = excepts[i].trim();
		}
	}

	public void doFilterInternal(
			HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException
	{
		String reqUri = request.getRequestURI();
		if( reqUri.startsWith("/wxcrm") )
		{
			reqUri = reqUri.substring(reqUri.indexOf("/", 1), reqUri.length());
		}
		WcAdmin admin = null;
		if(reqUri.startsWith("/mysite")||reqUri.contains("|mysite"))
		{
			admin = (WcAdmin) request.getSession().getAttribute(SysConstant.ADMIN_INFO_WX);
		}
		else
		{
			admin = (WcAdmin) request.getSession().getAttribute(SysConstant.ADMIN_INFO);
		}
		if( admin != null || PatternMatchUtils.simpleMatch(excepts, reqUri) || (reqUri.contains(".") && ! reqUri.toLowerCase().endsWith(".jsp")) )
		{
			// 把request保存在线程局部变量中
//			SysConstant.REQUEST_LOCAL.set(request);
			// 执行目标方法
			filterChain.doFilter(request, response);
			// 清空线程局部变量
//			SysConstant.REQUEST_LOCAL.remove();
		}
		else 
		{
			if( request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest") )
			{
				response.setStatus(999);
			}
			else 
			{
				if( ! reqUri.contains("/add") && ! reqUri.contains("/upd") && ! reqUri.contains("/del") )
				{
					Cookie cookieUri = new Cookie(SysConstant.LOGIN_REDIRECT_URI, reqUri);
					cookieUri.setMaxAge(SysConstant.COOKIE_AGE);
					cookieUri.setPath("/");
					response.addCookie(cookieUri);
				}
				String aulous = request.getParameter("aulous");
				if( aulous == null )
				{
					request.setAttribute("msgCode", "1");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/view/common/msg.jsp");
					dispatcher.forward(request, response);
				}
				else 
				{
					response.sendRedirect(request.getContextPath() + "/admin/adminLoginSpark?username=" + aulous + "&pwd=0&noVali=true");
				}
			}
		}
	}
}
