package com.springLataleLauncher.demo.DTO;

public interface ServerService {
    java.util.List<ServerInfo> getAllServers();
    java.util.List<ServerInfo> getFallbackServers();
    ServerSelectionResponse selectServer(String serverName);
}
