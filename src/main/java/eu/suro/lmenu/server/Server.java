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


    public void CreateServer(){

    }

    public void DeleteServer(){

    }

    public void ListServers(){
        stub.listServers(ServerOuterClass.Empty.newBuilder().buildPartial(),
                new ListServers());
    }

    class ListServers implements StreamObserver<ServerOuterClass.ListServersResponse> {

        @Override
        public void onNext(ServerOuterClass.ListServersResponse value) {
            LaunchMenu.servers = value.getServersList();
        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onCompleted() {

        }
    }

}
