package com.swmu.jos.service;

import com.google.firebase.FirebaseApp;
import com.swmu.jos.model.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final FirebaseApp app;

    private final List<Message> messages = new ArrayList<>();

    public MessageService(final FirebaseApp app) {
        this.app = app;
    }

    public Collection<Message> getAll() throws IOException {
        return messages.stream().toList();
    }

    public void save(final Message message) throws IOException {

    }
}
