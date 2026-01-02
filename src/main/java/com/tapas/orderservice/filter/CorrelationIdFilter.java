package com.tapas.orderservice.filter;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorrelationIdFilter extends OncePerRequestFilter {
	
	private static final String CORRELATION_ID = "X-Correlation-Id";
	private static final String MDC_KEY = "correlationId";
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			
			String correlationId =  request.getHeader(CORRELATION_ID);
			
			if(correlationId == null || correlationId.isEmpty()) {
				correlationId = UUID.randomUUID().toString();
			}
			
			MDC.put(MDC_KEY, correlationId);
			
			response.setHeader(CORRELATION_ID, correlationId);
			
			filterChain.doFilter(request, response);
			
		}catch (Exception e) {
			MDC.clear();
		}
	}	

	
	

}
