package ru.naumow.filters;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.Lifecycle;
import org.springframework.context.LifecycleProcessor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.WebUtils;
import ru.naumow.dto.UserDto;
import ru.naumow.entity.User;
import ru.naumow.services.SecurityService;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Objects;

@Component
public class ChatSecurityFilter extends GenericFilterBean {
    private final Log logger = LogFactory.getLog(getClass());

    @Autowired private SecurityService securityService;
    @Autowired private Environment     environment;

    public ChatSecurityFilter() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = ((HttpServletRequest) request);
            HttpServletResponse httpResponse = ((HttpServletResponse) response);

            if (logger.isInfoEnabled()) {
                logger.info(getFilterName() + ": is handling " + httpRequest.getRequestURL());
            }

            UserDto userDto = authorize(httpRequest);
            boolean isAuthorized = Objects.nonNull(userDto);

            if (isAuthorized) {
                if (logger.isInfoEnabled()) {
                    logger.info(getFilterName() + ": authorized " + httpRequest.getRequestURL());
                }
                chain.doFilter(wrappedWithPrincipal(userDto, httpRequest), httpResponse);
            } else {
                if (logger.isErrorEnabled()) {
                    logger.info(getFilterName() + ": error while authorizing " + httpRequest.getRequestURL());
                }
                httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        } else {
            throw new IllegalStateException("only http supported");
        }
    }

    private UserDto authorize(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, environment
                .getRequiredProperty("security.cookie.name"));
        if (cookie == null) {
            return null;
        }
        String token = cookie.getValue();
        try {
            return securityService.authorizeByToken(token);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private HttpServletRequest wrappedWithPrincipal(Principal principal, HttpServletRequest request) {
        return new HttpServletRequestWrapper(request) {
            @Override
            public Principal getUserPrincipal() {
                return principal;
            }
        };
    }

}
