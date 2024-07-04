package com.api.antonio.config;

import com.api.antonio.modelo.ApiKey;
import com.api.antonio.repositorio.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor {

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String apiKeyHeader = request.getHeader("Api-Key");

        if (apiKeyHeader == null || !isValidApiKey(apiKeyHeader)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        return true;
    }

    private boolean isValidApiKey(String apiKeyHeader) {
        ApiKey apiKeyEntity = apiKeyRepository.findByApiKey(apiKeyHeader);
        return apiKeyEntity != null;
    }
}
