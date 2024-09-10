package com.api.antonio.config;

import com.api.antonio.modelo.ApiKey;
import com.api.antonio.repositorio.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class ApiKeyAuthenticationToken extends AbstractAuthenticationToken {

    private final ApiKey apiKeyEntity;

    // Constructor inyectando el repositorio
    public ApiKeyAuthenticationToken(ApiKey apiKeyEntity) {
        super(null);
        this.apiKeyEntity = apiKeyEntity;
        setAuthenticated(true);  // La autenticación se considera exitosa al crear este token
    }

    @Override
    public Object getCredentials() {
        return null;  // No hay credenciales adicionales para la autenticación con API Key
    }

    @Override
    public Object getPrincipal() {
        return this.apiKeyEntity;
    }
}
