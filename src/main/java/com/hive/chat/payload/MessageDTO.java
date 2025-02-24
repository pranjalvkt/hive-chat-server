package com.hive.chat.payload;

import java.time.Instant;

public class MessageDTO {
    private String content;
    private Instant timeStamp;
    private String senderName;
    private ProfilePicDTO senderProfilePicture;
    private String userId;

    public MessageDTO(String content, Instant timeStamp, String userId, String user_name, ProfilePicDTO profilePic) {
        this.content = content;
        this.timeStamp = timeStamp;
        this.userId = userId;
        this.senderName = user_name;
        this.senderProfilePicture = profilePic;
    }

    public String getContent() { return content; }
    public Instant getTimeStamp() { return timeStamp; }
    public String getUserId() { return  userId; }
    public String getSenderName() { return senderName; }
    public ProfilePicDTO getSenderProfilePicture() { return senderProfilePicture; }
}
