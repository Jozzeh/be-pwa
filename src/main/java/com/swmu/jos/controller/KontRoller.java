package com.swmu.jos.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.swmu.jos.model.Message;
import com.swmu.jos.service.MessageService;
import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class KontRoller {

    private final MessageService messageService;

    public KontRoller(final MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public Collection<Message> all() {
        return this.messageService.getAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void save(@RequestBody final Message message) {
        this.messageService.save(message);
    }
}
