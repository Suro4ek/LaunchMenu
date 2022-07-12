package eu.suro.lmenu.server;

import io.grpc.stub.StreamObserver;
import server.ServerGrpc;
import server.ServerOuterClass;
import user.UserGrpc;
import user.UserOuterClass;

import java.util.Locale;

public class User {
    private UserGrpc.UserBlockingStub stub;

    public User(UserGrpc.UserBlockingStub stub){
        this.stub = stub;
    }

    public UserOuterClass.UserM getUser(String name){
        UserOuterClass.UserM userm = UserOuterClass.UserM.newBuilder().build();
//        GetUser user = new GetUser(userm);
        UserOuterClass.GetUserResponse userResponse = this.stub.getUser(UserOuterClass.GetUserRequest.
                newBuilder().
                setName(name.toLowerCase(Locale.ROOT)).
                build());
        return userResponse.getUser();
    }

    public void CreateUser(String name){
        UserOuterClass.CreateUserRequest request = UserOuterClass.CreateUserRequest.newBuilder()
                .setName(name).buildPartial();
        UserOuterClass.Response response = this.stub.createUser(request);
    }

//    class GetUser implements StreamObserver<UserOuterClass.GetUserResponse>{
//        UserOuterClass.UserM userm;
//        public GetUser(UserOuterClass.UserM userm){
//            this.userm = userm;
//        }
//
//        @Override
//        public void onNext(UserOuterClass.GetUserResponse value) {
//            this.userm = value.getUser();
//        }
//
//        @Override
//        public void onError(Throwable t) {
//
//        }
//
//        @Override
//        public void onCompleted() {
//
//        }
//    }

    public void removeFriend(String username, String friend_name){
        UserOuterClass.Response response = stub.removeFriend(UserOuterClass.RemoveFriendRequest
                .newBuilder()
                        .setName(username.toLowerCase(Locale.ROOT))
                        .setFriendName(friend_name.toLowerCase(Locale.ROOT))
                .build());
    }

    public void addFriend(String username, String friend_name){

    }


    public void RemoveWorld(String username){

    }
//    class FriendCallback implements StreamObserver<UserOuterClass.Response>{
//        @Override
//        public void onNext(UserOuterClass.Response value) {
//
//        }
//
//        @Override
//        public void onError(Throwable t) {
//
//        }
//
//        @Override
//        public void onCompleted() {
//
//        }
//    }
}
