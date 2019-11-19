package com.proky.booking.filter;

import com.proky.booking.util.constans.Attributes;
import com.proky.booking.util.constans.Parameters;
import com.proky.booking.util.constans.UserTypeEnum;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static com.proky.booking.util.constans.Commands.*;
import static com.proky.booking.util.properties.ViewProperties.*;

public class ServletSecurityFilter implements Filter {
    private static final Logger log = LogManager.getLogger(ServletSecurityFilter.class);
    private static final Set<String> forbiddenCommands = new HashSet<>();

    static {
        forbiddenCommands.add(MANAGE_USER);
        forbiddenCommands.add(DELETE_USER);
        forbiddenCommands.add(UPDATE_USER);
        forbiddenCommands.add(ALL_USERS);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("in ServletSecurityFilter");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        final String accessDeniedErrorPath = ViewProperties.getValue(ERROR_ACCESS_DENIED);

        String URI = httpRequest.getRequestURI();
        String URL = httpRequest.getRequestURL().toString();
        String command = httpRequest.getParameter(Parameters.COMMAND);
        final String contextPath = httpRequest.getContextPath();

        log.debug("SECURITY Resource: {}", URI);
        log.debug("SECURITY URL: {}", URL);

        final UserTypeEnum currentUserType = getCurrentUserType(session);
        if (!currentUserType.equals(UserTypeEnum.GUEST)) {
            session.setAttribute(Attributes.USER_TYPE, currentUserType);
        }

        final boolean isForbiddenCommand = isForbiddenCommand(command);

        if (isForbiddenCommand && !currentUserType.equals(UserTypeEnum.ADMIN)) {
            httpRequest.getRequestDispatcher(contextPath + accessDeniedErrorPath).forward(httpRequest, httpResponse);
            log.debug("forbidden command! Only admin can do the command!");
        } else {
            chain.doFilter(request, response);
        }
    }

    static UserTypeEnum getCurrentUserType(HttpSession session) {
        UserTypeEnum userType = UserTypeEnum.GUEST;
        if (session != null) {
            final Object userTypeAttribute = session.getAttribute(Attributes.USER_TYPE);
            if (userTypeAttribute != null) {
                userType = (UserTypeEnum) session.getAttribute(Attributes.USER_TYPE);
            }
        }
        return userType;
    }


    private boolean isForbiddenCommand(String command) {
        return command != null && forbiddenCommands.contains(command);
    }

    @Override
    public void destroy() { }
}
