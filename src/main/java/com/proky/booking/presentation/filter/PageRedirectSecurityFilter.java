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

/**
 * The class restricts access to forbidden URL paths.
 */
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
        log.info("in PageRedirectSecurityFilter");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        final String contextPath = httpRequest.getContextPath();
        String uri = httpRequest.getRequestURI().replace(contextPath, "");

        UserTypeEnum userType = CommandUtil.getCurrentUserType(session);

        final boolean isAdmin = userType.equals(UserTypeEnum.ADMIN);
        final boolean isUser = userType.equals(UserTypeEnum.USER);

        final boolean isForbidden = isForbiddenURI(uri);
        final boolean isSignInOrSignUp = isSignInOrSignUp(uri);

        if (isForbidden) {
            log.debug("Forbidden path");
            if (isAdmin) {
                chain.doFilter(request, response);
            } else {
                log.info("not found");
                httpRequest.getRequestDispatcher(notFoundErrorPath).forward(request, response);
            }
        } else if(isSignInOrSignUp && (isUser || isAdmin)) {
            httpResponse.sendRedirect(contextPath + indexPath);
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean isSignInOrSignUp(String uri) {
        return uri.startsWith(ViewProperties.getValue(SIGN_IN)) || uri.startsWith(ViewProperties.getValue(SIGN_UP));
    }

    private boolean isForbiddenURI(String uri) {
        log.info(uri);

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
