package eu.suro.lmenu.server;

import eu.suro.lmenu.LaunchMenu;
import io.grpc.stub.StreamObserver;
import server.ServerGrpc;
import server.ServerOuterClass;

public class Server {

    private ServerGrpc.ServerStub stub;

    public Server(ServerGrpc.ServerStub stub){
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
        this.stub.createServer(request, new ServerCallBack());
    }

    public void DeleteServer(int port){
        ServerOuterClass.DeleteServerRequest request = ServerOuterClass.DeleteServerRequest
                .newBuilder()
                .setPort(port)
                .buildPartial();
        this.stub.deleteServer(request, new ServerCallBack());
    }

    public void ListServers(){
        stub.listServers(ServerOuterClass.Empty.newBuilder().buildPartial(),
                new ListServers());
    }

    class ServerCallBack implements  StreamObserver<ServerOuterClass.Response>{

        @Override
        public void onNext(ServerOuterClass.Response value) {

        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onCompleted() {

        }
    }

    class ListServers implements StreamObserver<ServerOuterClass.ListServersResponse> {

        @Override
        public void onNext(ServerOuterClass.ListServersResponse value) {
            LaunchMenu.getInstance().setServers(value.getServersList());
        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onCompleted() {

        }
    }

}
