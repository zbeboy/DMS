package top.zbeboy.dms.filter;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import top.zbeboy.dms.domain.dms.tables.pojos.Users;
import top.zbeboy.dms.security.AjaxAuthenticationCode;
import top.zbeboy.dms.service.platform.UsersService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lenovo on 2016-09-03.
 * 安全登录配置
 */
public class SecurityLoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if ("POST".equalsIgnoreCase(request.getMethod()) && request.getRequestURI().endsWith("/login")) {
            String username = StringUtils.trimWhitespace(request.getParameter("username"));
            String password = StringUtils.trimWhitespace(request.getParameter("password"));
            if (!StringUtils.hasLength(username)) {// 账号不为空
                response.getWriter().print(AjaxAuthenticationCode.EMAIL_IS_BLANK);
                return;
            }

            if (!StringUtils.hasLength(password)) {// 密码不为空
                response.getWriter().print(AjaxAuthenticationCode.PASSWORD_IS_BLANK);
                return;
            }

            ServletContext context = request.getSession().getServletContext();
            WebApplicationContext ctx = WebApplicationContextUtils
                    .getWebApplicationContext(context);
            assert ctx != null;
            UsersService usersService = (UsersService)ctx
                    .getBean("usersService");
            Users users = usersService.findByUsername(username);

            if (ObjectUtils.isEmpty(users)) {// 用户是否存在
                response.getWriter().print(AjaxAuthenticationCode.USERNAME_IS_NOT_EXIST_CODE);
                return;
            }

            if (ObjectUtils.isEmpty(users.getEnabled()) || !users.getEnabled()) {// 用户是否已被注销
                response.getWriter().print(AjaxAuthenticationCode.USERNAME_IS_ENABLES);
                return;
            }

            if (ObjectUtils.isEmpty(users.getAccountNonExpired()) || !users.getAccountNonExpired()) {// 用户是否账号过期
                response.getWriter().print(AjaxAuthenticationCode.USERNAME_ACCOUNT_NON_EXPIRED);
                return;
            }

            if (ObjectUtils.isEmpty(users.getCredentialsNonExpired()) || !users.getCredentialsNonExpired()) {// 用户是否凭证过期
                response.getWriter().print(AjaxAuthenticationCode.USERNAME_CREDENTIALS_NON_EXPIRED);
                return;
            }

            if (ObjectUtils.isEmpty(users.getAccountNonLocked()) || !users.getAccountNonLocked()) {// 用户是否被锁
                response.getWriter().print(AjaxAuthenticationCode.USERNAME_ACCOUNT_NON_LOCKED);
                return;
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }


}
