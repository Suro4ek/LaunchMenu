package eu.suro.lmenu.server;

import eu.suro.lmenu.LaunchMenu;
import io.grpc.stub.StreamObserver;
import server.ServerGrpc;
import server.ServerOuterClass;

public class Server {

    private ServerGrpc.ServerBlockingStub stub;

    public Server(ServerGrpc.ServerBlockingStub stub){
        this.stub = stub;
    }

    public void CreateServer(String name, boolean open, boolean save_world, String version){
        ServerOuterClass.CreateServerRequest request = ServerOuterClass.CreateServerRequest
                        .newBuilder()
                                .setName(name)
                                .setOpen(open)
                                .setSaveWorld(save_world)
                                .setVersion(version)
                .buildPartial();
       ServerOuterClass.Response response = this.stub.createServer(request);
    }

    public void DeleteServer(int port){
        ServerOuterClass.DeleteServerRequest request = ServerOuterClass.DeleteServerRequest
                .newBuilder()
                .setPort(port)
                .buildPartial();
        ServerOuterClass.Response response = this.stub.deleteServer(request);
    }

    public void ListServers(){
       ServerOuterClass.ListServersResponse response = stub.listServers(ServerOuterClass.Empty.newBuilder().buildPartial());
       LaunchMenu.getInstance().setServers(response.getServersList());
    }

//    class ServerCallBack implements  StreamObserver<ServerOuterClass.Response>{
//
//        @Override
//        public void onNext(ServerOuterClass.Response value) {
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

//    class ListServers implements StreamObserver<ServerOuterClass.ListServersResponse> {
//
//        @Override
//        public void onNext(ServerOuterClass.ListServersResponse value) {
//            LaunchMenu.getInstance().setServers(value.getServersList());
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
