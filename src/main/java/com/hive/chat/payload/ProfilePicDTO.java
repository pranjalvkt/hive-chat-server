package com.hive.chat.payload;

public class ProfilePicDTO {
    private Object data;  // Using Object here since JSON shows {}
    private String contentType;

    public ProfilePicDTO() {}

    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public String getContentType() {
        return contentType;
    }
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
