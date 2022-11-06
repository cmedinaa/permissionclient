package com.cmedinaa.permissions.client.conversion.builders.impl;

import com.cmedinaa.permissions.client.conversion.builders.WebClientBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientBuilderImpl implements WebClientBuilder {

    @Override
    public WebClient forUrl(String url) {
        return WebClient.builder()
                .baseUrl(url)
                .filter((request, next) ->  {
                    String jwtAccessToken = ((DefaultOidcUser) SecurityContextHolder.getContext()
                            .getAuthentication()
                            .getPrincipal())
                            .getIdToken()
                            .getTokenValue();

                    ClientRequest filtered = ClientRequest.from(request)
                            .headers(httpHeaders -> httpHeaders.setBearerAuth(jwtAccessToken))
                            .build();

                    return next.exchange(filtered);
                })
                .build();
    }
}
