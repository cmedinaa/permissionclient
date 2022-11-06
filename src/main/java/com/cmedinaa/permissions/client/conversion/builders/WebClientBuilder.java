package com.cmedinaa.permissions.client.conversion.builders;

import org.springframework.web.reactive.function.client.WebClient;

public interface WebClientBuilder {

    /**
     *
     * @param url
     * @return
     */
    public WebClient forUrl(String url);
}
