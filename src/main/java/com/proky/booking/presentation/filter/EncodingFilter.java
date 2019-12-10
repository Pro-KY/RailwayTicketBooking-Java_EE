package com.proky.booking.presentation.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * The class applies an encoding configured in web.xml to request content.
 */
public class EncodingFilter implements Filter {
    private String code;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String codeRequest = servletRequest.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            servletRequest.setCharacterEncoding(code);
            servletResponse.setCharacterEncoding(code);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        code = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void destroy() {
        code = null;
    }
}
