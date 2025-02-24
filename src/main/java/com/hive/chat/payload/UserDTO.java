package com.hive.chat.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {
    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("user_name")
    private String name;

    private ProfilePicDTO profilePic;

    // No-args constructor is required for deserialization
    public UserDTO() {}

    // You can also have a constructor for fallback values
    public UserDTO(String userId, String name, ProfilePicDTO profilePic) {
        this.userId = userId;
        this.name = name;
        this.profilePic = profilePic;
    }

    // Getters and setters
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ProfilePicDTO getProfilePic() {
        return profilePic;
    }
    public void setProfilePic(ProfilePicDTO profilePic) {
        this.profilePic = profilePic;
    }
}
