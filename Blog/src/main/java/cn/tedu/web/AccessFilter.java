package cn.tedu.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.tedu.entity.User;

/**
 * 	访问权限检查过滤器
 * @author 86138
 *
 */
public class AccessFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//检查session中是否有登录信息
		//1.如果有登录信息 就放过web请求
		//2.如果没有登录信息 就重定向到登录页面
		
		//为了方便的使用 更多的子类API方法
		//将ServletRequest转换为HttpServletRequest
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		//转换以后 req和request 引用同一个对象
		HttpSession session=req.getSession();
		//获取登录用户信息
		User user=(User) session.getAttribute("user");
		System.out.println("用户："+user);
		if(user==null) {
			//没有登录信息 也就是没有登录
			System.out.println("重定向到登录页");
			String location=req.getContextPath()+"/ShowLoginServlet";
			res.sendRedirect(location);
		}else {
			//有登录信息 也就是登录成功
			System.out.println("放过web请求");
			chain.doFilter(req, res);
			System.out.println("后续链条处理完毕");
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
