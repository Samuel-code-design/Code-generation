package io.swagger.security.filters;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@Component
@Order(1)
public class LargeRequestFilter implements Filter {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
            IOException, ServletException {
        int size = request.getContentLength();
        logger.info("Request size: " + size);
        if (size > 100) {
            logger.severe( "request with size " + size + " was rejected");
            throw new ServletException("Request too large");
        } else {
            chain.doFilter(request, response); // pass on to the next filter
        }
    }
}