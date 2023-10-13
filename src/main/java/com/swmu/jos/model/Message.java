package com.swmu.jos.model;

import java.util.Objects;
import java.util.UUID;

public class Message {

    private final UUID id;
    private final long timestamp;
    private final String message;
    private final String username;
    private final String token;

    public Message(final UUID id, final long timestamp, final String message,
        final String username, final String token) {
        this.id = id;
        this.timestamp = timestamp;
        this.message = message;
        this.username = username;
        this.token = token;
    }

    public UUID getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Message message1 = (Message) o;
        return Objects.equals(id, message1.id) && Objects.equals(timestamp, message1.timestamp)
            && Objects.equals(message, message1.message) && Objects.equals(username,
            message1.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, message, username);
    }
}
