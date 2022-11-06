package com.cmedinaa.permissions.client.services.impl;

import com.cmedinaa.permissions.client.conversion.builders.WebClientBuilder;
import com.cmedinaa.permissions.client.conversion.dto.PermissionDTO;
import com.cmedinaa.permissions.client.services.ResourceProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ResourceProxyServiceImpl implements ResourceProxyService {
    @Value("${resourceUrl}")
    private String resourceUrl;

    @Autowired
    private WebClientBuilder webClientBuilder;

    @Override
    public List<PermissionDTO> getPermissions() {
        PermissionDTO[] results = webClientBuilder.forUrl(resourceUrl)
                .get()
                .uri("/permissions/all")
                .retrieve()
                .bodyToMono(PermissionDTO[].class)
                .doOnError(throwable -> System.err.println("Received error " + throwable))
                .block()
                ;

        return Optional.ofNullable(results)
                .map(Arrays::asList)
                .orElse(Collections.<PermissionDTO>emptyList());
    }
}
