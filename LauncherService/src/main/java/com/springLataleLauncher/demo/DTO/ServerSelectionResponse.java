package com.springLataleLauncher.demo.DTO;

/**
 * DTO describing the outcome of a server selection request.
 */

public class ServerSelectionResponse {
    private String serverName;
    private String status;
    private String message;

    /**
     * Creates an empty selection response for serializers.
     */
    public ServerSelectionResponse() {
    }

    /**
     * Creates a response with a status message for the selected server.
     */
    public ServerSelectionResponse() {
    }

    public ServerSelectionResponse(String serverName, String status, String message) {
        this.serverName = serverName;
        this.status = status;
        this.message = message;
    }

    /**
     * Builds a fallback response when selection cannot be processed.
     */
    public static ServerSelectionResponse fallback(String serverName) {
        return new ServerSelectionResponse(serverName, "queued", "Server selection queued. Please retry.");
    }

    /**
     * Returns the selected server name.
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * Sets the selected server name.
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    /**
     * Returns the selection status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the selection status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the message for the selection response.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message for the selection response.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
