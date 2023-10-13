package com.swmu.jos.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.swmu.jos.model.Message;
import com.swmu.jos.model.PushNotificationRequest;
import com.swmu.jos.service.MessageService;
import com.swmu.jos.service.PushNotificationService;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin("*")
public class KontRoller {

    private final MessageService messageService;
    private final PushNotificationService pushNotificationService;

    public KontRoller(final MessageService messageService,
        final PushNotificationService pushNotificationService) {
        this.messageService = messageService;
        this.pushNotificationService = pushNotificationService;
    }

    @GetMapping
    public Collection<Message> all() throws IOException, ExecutionException, InterruptedException {
        return this.messageService.getAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void save(@RequestBody final Message message) throws IOException, ExecutionException, InterruptedException {

        final var notification = new PushNotificationRequest();
        notification.setMessage(message.getMessage());
        notification.setTitle("SWEET MUSTARD!!!");
        notification.setTopic("jos");
        notification.setToken(message.getToken());

        this.pushNotificationService.sendPushNotification(notification);

        this.messageService.save(message);
    }
}
