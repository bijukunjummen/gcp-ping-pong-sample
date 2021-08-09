package org.bk.consumer.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RootConfiguration {
    @Bean
    @Scope("prototype")
    public WebClient.Builder webClientBuilder(ObjectProvider<WebClientCustomizer> customizerProvider) {
        WebClient.Builder builder = WebClient.builder();
        customizerProvider.orderedStream().forEach((customizer) -> {
            customizer.customize(builder);
        });
        return builder;
    }
}
