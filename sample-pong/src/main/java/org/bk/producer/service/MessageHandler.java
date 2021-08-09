package org.bk.producer.service;

import org.bk.producer.domain.Message;
import org.bk.producer.domain.MessageAcknowledgement;
import reactor.core.publisher.Mono;

public interface MessageHandler {
    Mono<MessageAcknowledgement> handleMessage(Message message);
}
