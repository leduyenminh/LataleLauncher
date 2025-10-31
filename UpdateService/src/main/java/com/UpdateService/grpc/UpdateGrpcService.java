package com.UpdateService.grpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import update.UpdateServiceGrpc;
import update.UpdateRequest;
import update.UpdateResponse;
import update.CurrentVersion;

/**
 * Service implementation for the UpdateService gRPC service.
 */
@GrpcService
public class UpdateGrpcService extends UpdateServiceGrpc.UpdateServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(UpdateGrpcService.class);
    /**
     * Handles the CheckForUpdate RPC call.
     * 
     * @param request the UpdateRequest from the client
     * @param responseObserver the observer to send the UpdateResponse back to the client
     */
    @Override
    public void checkForUpdate(UpdateRequest request, StreamObserver<UpdateResponse> responseObserver) {
        // Logic to determine the latest version
        int clientVersion = request.getCurrentVersion();
        int serverLatestVersion = 102; // Example latest version
        logger.info("Checking for update...");
        logger.info("Current version: " + clientVersion);
        logger.info("Updating...");
       

        // Build the response
        UpdateResponse response = UpdateResponse.newBuilder()
                .setLatestVersion(serverLatestVersion)
                .build();

        // Send the response back to the client
        responseObserver.onNext(response);
        // Complete the RPC call
        responseObserver.onCompleted();
        logger.info("Update complete!");
    }

    /**
     * Handles the GetCurrentVersion RPC call.
     * 
     * @param request (empty)
     * @param responseObserver the observer to send the CurrentVersion back to the client
     */
    @Override
    public void getCurrentVersion(com.google.protobuf.Empty request, StreamObserver<CurrentVersion> responseObserver) {
        // Logic to get the current server version
        int currentServerVersion = 101; // Example current version

        // Build the response
        CurrentVersion response = CurrentVersion.newBuilder()
                .setCurrentVersion(currentServerVersion)
                .build();

        // Send the response back to the client
        responseObserver.onNext(response);
        // Complete the RPC call
        responseObserver.onCompleted();
    }
}
