package com.seyan.redis.model;


public class MyMessage {
    private String sessionid;
    private String userName;
    private String content;

    /**
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionid;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @param sessionId the sessionId to set
     */
    public void setSessionId(String sessionid) {
        this.sessionid = sessionid;
    }

    

}