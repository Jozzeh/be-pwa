package com.swmu.jos.service;

import com.swmu.jos.model.Message;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final List<Message> messages = new ArrayList<>();

    public Collection<Message> getAll() {
        return messages.stream().toList();
    }

    public void save(final Message message) {
        this.messages.add(message);
    }
}
