package coupon.filter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import coupon.bean.UserDataMap;
import coupon.idao.ICacheManager;

import java.io.IOException;


@Component
@WebFilter("/*")
public class LoginFilter implements Filter {


    @Autowired
    private ICacheManager cacheManager;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = ((HttpServletRequest) request);
        String path = req.getRequestURI();
        if (path.startsWith("/user/login")) {

            chain.doFilter(request, response); // Just continue chain.
            return;

        }

        // Create
        if (path.startsWith("/customer") && req.getMethod()
                .equalsIgnoreCase("post"))
        {

            chain.doFilter(request, response); // Just continue chain.
            return;

        }

        Integer token = Integer.parseInt(req.getParameter("token"));

        UserDataMap userData = (UserDataMap) cacheManager.get(token);

        if (userData != null) {

            request.setAttribute("userData", userData);
            chain.doFilter(request, response);
            return;

        }

        HttpServletResponse res = (HttpServletResponse) response;
//	        401 = Unauthorized http error code
        res.setStatus(401);


    }

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}