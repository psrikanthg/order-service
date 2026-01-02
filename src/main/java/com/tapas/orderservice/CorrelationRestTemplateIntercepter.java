package com.tapas.orderservice;

import java.io.IOException;

import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class CorrelationRestTemplateIntercepter implements ClientHttpRequestInterceptor{

	private static final String HEADER = "X-Correlation-Id";
	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		
		String correlationId = MDC.get("correlationId");

        if (correlationId != null) {
            request.getHeaders().add(HEADER, correlationId);
        }

        return execution.execute(request, body);
	}

}
