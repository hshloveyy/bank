package com.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 这个是个过滤器，配置在web.xml中
 * @ClassName safeFileter
 * @Description TODO
 * @date 2016年3月30日 下午1:14:45
 */
public class safeFileter implements Filter {

	public void destroy()
	{
		
    }

	/**
	 * 此方法在每次请求中都会执行，在本工程中并未使用
	 */
	public void doFilter(ServletRequest arg0, ServletResponse arg1,FilterChain arg2) throws IOException, ServletException 
	{
		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response=(HttpServletResponse)arg1;
		if(request.getSession().getAttribute("admin")==null)
		{
			String path=request.getContentType();
			response.sendRedirect(path+"/error1.html");
		}
		else
		{
			arg2.doFilter(request,response);
		}
	}	
		
		/*web.xml
		<filter>
	       <filter-name>safeFileter</filter-name>
	       <filter-class>com.bookstore.util.safeFilter</filter-class>
	    </filter>
	    <filter-mapping>
	       <filter-name>safeFileter</filter-name>
	       <url-pattern>/jsp</url-pattern>
	    </filter-mapping>*/

	

	public void init(FilterConfig arg0) throws ServletException 
	{



	}

}
