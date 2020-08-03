package lk.controller;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 *
 * @author likeLove
 * @time 2020-08-02  15:25
 */
public class Interceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String login = (String) request.getSession().getAttribute("login");
        if ("true".equals(login)) {
            return true;
        }
       /* if (request.getRequestURI().contains("login")) {
            return true;
        }*/
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        return false;
    }
}
