package cn.cyanbean.user;

import cn.cyanbean.grpc.User;
import cn.cyanbean.grpc.userGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class UserClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();
        userGrpc.userBlockingStub userStub = userGrpc.newBlockingStub(channel);
        User.LoginRequest request = User.LoginRequest.newBuilder().setUsername("username").setPassword("username").build();

        User.APIResponse response = userStub.login(request);
        System.out.printf("get response. code: %d, msg: %s", response.getCode(), response.getMsg());
    }
}
