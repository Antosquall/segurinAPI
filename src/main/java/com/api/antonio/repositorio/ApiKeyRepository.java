package com.api.antonio.repositorio;

import com.api.antonio.modelo.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

    ApiKey findByApiKey(String apiKey);
}
