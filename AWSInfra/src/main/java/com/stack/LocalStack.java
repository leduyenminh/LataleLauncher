package com.stack;

import com.amazonaws.services.ecs.model.Cluster;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.ec2.Vpc;

public class LocalStack extends Stack {
    private final Vpc vpc;
    private final Cluster ecsCluster;
}
