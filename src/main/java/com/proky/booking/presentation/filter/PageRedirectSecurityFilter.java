package com.proky.booking.presentation.filter;

import com.proky.booking.presentation.command.CommandUtil;
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

import static com.proky.booking.util.properties.ViewProperties.*;


public class PageRedirectSecurityFilter implements Filter {
    private static final Logger log = LogManager.getLogger(PageRedirectSecurityFilter.class);
    private static final Set<String> forbiddenViewPaths = new HashSet<>();
    private String indexPath;
    private final String notFoundErrorPath = ViewProperties.getValue(ERROR_NOT_FOUND);

    static {
        forbiddenViewPaths.add(ViewProperties.getValue(ADMIN_USERS));
        forbiddenViewPaths.add(ViewProperties.getValue(ADMIN_MANAGE_USER));
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        indexPath = filterConfig.getInitParameter("indexPath");
        log.info(indexPath);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        log.info("in PageRedirectSecurityFilter");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        final String contextPath = httpRequest.getContextPath();
        String uri = httpRequest.getRequestURI();
//        log.info("REDIRECT Resource: {}", uri);

        String adminPath = contextPath + "/jsp/admin";
//        log.info("adminPath {}", adminPath);

        UserTypeEnum userType = CommandUtil.getCurrentUserType(session);
//        log.info("userType is {}", userType);

        final boolean isAdmin = userType.equals(UserTypeEnum.ADMIN);
        final boolean isUser = userType.equals(UserTypeEnum.USER);

        final boolean isForbidden = isForbiddenURI(uri);
        final boolean isSignInOrSignUp = isSignInOrSignUp(uri);

        if (isForbidden) {
            log.debug("Forbidden path");
            if (isAdmin) {
                chain.doFilter(request, response);
            } else {
                httpRequest.getRequestDispatcher(contextPath + notFoundErrorPath).forward(request, response);
            }
        } else if(isSignInOrSignUp && (isUser || isAdmin)) {
//            log.debug("signed_in user tried to sign_in or sign_up again!");
            httpResponse.sendRedirect(contextPath + indexPath);
//            httpRequest.getRequestDispatcher(contextPath + indexPath).forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean isSignInOrSignUp(String uri) {
        return uri.startsWith(ViewProperties.getValue(SIGN_IN)) || uri.startsWith(ViewProperties.getValue(SIGN_UP));
    }

    private boolean isForbiddenURI(String uri) {
        boolean isForbidden = false;
        for (String forbiddenPath : forbiddenViewPaths) {
            if (uri.startsWith(forbiddenPath)) {
                isForbidden = true;
                break;
            }
        }

        return isForbidden;
    }

    @Override
    public void destroy() {}
}
