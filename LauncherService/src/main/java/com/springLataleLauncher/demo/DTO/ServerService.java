package com.springLataleLauncher.demo.DTO;

/**
 * Launcher server service contract for listing servers and handling selection.
 */
public interface ServerService {
    /**
     * Fetches all known servers for display in the launcher.
     */
    java.util.List<ServerInfo> getAllServers();
    /**
     * Returns fallback servers when the primary data source is unavailable.
     */
    java.util.List<ServerInfo> getFallbackServers();
    /**
     * Handles selection of a server by name.
     */
    java.util.List<ServerInfo> getAllServers();
    java.util.List<ServerInfo> getFallbackServers();
    ServerSelectionResponse selectServer(String serverName);
}
