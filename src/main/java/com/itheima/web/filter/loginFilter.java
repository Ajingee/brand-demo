package com.itheima.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class loginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpSession session = ((HttpServletRequest) req).getSession();

        String[] usls = {"/login.jsp","/imgs/","/css/","loginServlet","register.jsp","/registerServlet","/checkCodeServlet"};

        String url = ((HttpServletRequest) req).getRequestURL().toString();

        for (String u : usls){
            if (url.contains(u)){
                chain.doFilter(req,resp);
                return;
            }
        }

        Object user = session.getAttribute("user");
        if (user != null){

        }else {
            req.setAttribute("login_msg","您尚未登录！！");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
