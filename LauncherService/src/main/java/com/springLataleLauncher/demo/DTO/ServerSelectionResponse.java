package com.springLataleLauncher.demo.DTO;

public class ServerSelectionResponse {
    private String serverName;
    private String status;
    private String message;

    public ServerSelectionResponse() {
    }

    public ServerSelectionResponse(String serverName, String status, String message) {
        this.serverName = serverName;
        this.status = status;
        this.message = message;
    }

    public static ServerSelectionResponse fallback(String serverName) {
        return new ServerSelectionResponse(serverName, "queued", "Server selection queued. Please retry.");
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
