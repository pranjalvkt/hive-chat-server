package com.hive.chat.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String sender;
    private String content;
    private Instant timeStamp;

    public Message(String content, String sender) {
        this.content = content;
        this.sender = sender;
        this.timeStamp = Instant.now();
    }

    public String getContent() {
        return content;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public String getSender() {
        return sender;
    }
}
