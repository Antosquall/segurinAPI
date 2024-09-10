package com.api.antonio.config;

import com.api.antonio.modelo.ApiKey;
import com.api.antonio.repositorio.ApiKeyRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import java.io.IOException;

public class ApiKeyAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

    private final String principalRequestHeader;
    private final ApiKeyRepository apiKeyRepository;

    public ApiKeyAuthenticationFilter(String principalRequestHeader, ApiKeyRepository apiKeyRepository, AuthenticationManager authenticationManager) {
        this.principalRequestHeader = principalRequestHeader;
        this.apiKeyRepository = apiKeyRepository;
        setAuthenticationManager(authenticationManager);
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return request.getHeader(principalRequestHeader);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return "N/A";
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String apiKey = request.getHeader(principalRequestHeader);

        if (apiKey == null || !isValidApiKey(apiKey)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        ApiKey apiKeyEntity = apiKeyRepository.findByApiKey(apiKey);
        if (apiKeyEntity == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        Authentication auth = new UsernamePasswordAuthenticationToken(apiKeyEntity, null, null);
        SecurityContextHolder.getContext().setAuthentication(auth);

        chain.doFilter(request, response);
    }

    private boolean isValidApiKey(String apiKey) {
        return apiKeyRepository.findByApiKey(apiKey) != null;
    }
}
