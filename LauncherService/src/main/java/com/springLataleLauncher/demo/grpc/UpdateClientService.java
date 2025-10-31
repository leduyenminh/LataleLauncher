package com.springLataleLauncher.demo.grpc; // Choose an appropriate package name

import com.google.protobuf.Empty; // Import if using Option 1 from the fix above
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import update.UpdateServiceGrpc;
import update.UpdateRequest;
import update.UpdateResponse;
import update.CurrentVersion; // Import the generated update package

@Service
public class UpdateClientService {

    /**
     * Injects a non-blocking stub for the UpdateService.
     * The name "global" matches the configuration key in application.properties.
     */
    @GrpcClient("global")
    private UpdateServiceGrpc.UpdateServiceBlockingStub blockingStub;

    public String checkForUpdate(int currentVersion) {
        try {
            System.out.println("Client sending request for version: " + currentVersion);
            
            // 1. Build the request message
            UpdateRequest request = UpdateRequest.newBuilder()
                    .setCurrentVersion(currentVersion)
                    .build();

            // 2. Call the remote RPC method using the blocking stub
            UpdateResponse response = blockingStub.checkForUpdate(request);

            // 3. Process the response
            return "Server's latest version is: " + response.getLatestVersion();

        } catch (StatusRuntimeException e) {
            // Handle gRPC specific errors
            return "RPC failed: " + e.getStatus().getDescription();
        }
    }

    public String getCurrentServerVersion() {
        try {
            // Use the standard Google Empty message for methods without parameters
            Empty request = Empty.newBuilder().build(); 

            // Call the remote RPC method
            CurrentVersion response = blockingStub.getCurrentVersion(request);

            return "Server reports its current version as: " + response.getCurrentVersion();

        } catch (StatusRuntimeException e) {
            return "RPC failed: " + e.getStatus().getDescription();
        }
    }
}
