package org.bk.consumer.service;

import java.net.URI;
import org.bk.consumer.domain.Message;
import org.bk.consumer.domain.MessageAcknowledgement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Component
public class WebClientPongClient implements PongClient {

  private final WebClient.Builder webClientBuilder;
  private final String baseUrl;

  public WebClientPongClient(
      WebClient.Builder webClientBuilder,
      @Value("${pong.baseUrl}") String baseUrl) {
    this.webClientBuilder = webClientBuilder;
    this.baseUrl = baseUrl;
  }

  @Override
  public Mono<MessageAcknowledgement> sendMessage(Message message) {
    URI uri = UriComponentsBuilder.fromUriString(baseUrl)
        .path("/message")
        .build()
        .toUri();
    return webClientBuilder.build()
        .post()
        .uri(uri)
        .body(BodyInserters.fromValue(message))
        .accept(MediaType.APPLICATION_JSON)
        .exchangeToMono(clientResponse -> clientResponse.bodyToMono(MessageAcknowledgement.class));
  }
}
