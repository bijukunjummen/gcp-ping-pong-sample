package org.bk.producer.controller;


import org.bk.producer.domain.Message;
import org.bk.producer.domain.MessageAcknowledgement;
import org.bk.producer.service.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class PongController {

    private final MessageHandler messageHandler;

    @Autowired
    public PongController(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public Mono<ResponseEntity<MessageAcknowledgement>> pongMessage(@RequestBody Message input) {
        return this.messageHandler.handleMessage(input).map(ack -> ResponseEntity.ok(ack));
    }

}
