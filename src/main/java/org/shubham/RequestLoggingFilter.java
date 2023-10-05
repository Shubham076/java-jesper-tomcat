package org.shubham;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class RequestLoggingFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLoggingFilter.class);
    private final SimpleDateFormat dateFormat;

    public RequestLoggingFilter() {
        dateFormat = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss Z");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));  // Adjust if needed
    }

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        chain.doFilter(request, response);  // Continue the request-response chain

        String logEntry = String.format(
                "%s - - [%s] \"%s %s %s\" %d",
                request.getRemoteAddr(),
                dateFormat.format(new Date()),
                httpRequest.getMethod(),
                httpRequest.getRequestURI(),
                httpRequest.getProtocol(),
                httpResponse.getStatus()
        );

        LOGGER.info(logEntry);
    }

    @Override
    public void destroy() {}
}
