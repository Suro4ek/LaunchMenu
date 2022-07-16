package eu.suro.lmenu.server;

import eu.suro.lmenu.LaunchMenu;
import io.grpc.stub.StreamObserver;
import org.bukkit.entity.Player;
import server.ServerGrpc;
import server.ServerOuterClass;

public class Server {

    private ServerGrpc.ServerStub stub;

    public Server(ServerGrpc.ServerStub stub){
        this.stub = stub;

    }

    public void CreateServer(Player player, String name, boolean open, boolean save_world, int version){
        ServerOuterClass.CreateServerRequest request = ServerOuterClass.CreateServerRequest
                        .newBuilder()
                                .setName(name)
                                .setOpen(open)
                                .setSaveWorld(save_world)
                                .setVersion(version)
                .buildPartial();
        this.stub.createServer(request, new StreamObserver<ServerOuterClass.Response>() {
            @Override
            public void onNext(ServerOuterClass.Response value) {
                if(player != null){
                    player.sendMessage("§aСервер запускается...");
                }
                LaunchMenu.getInstance().getLogger().info("Server created");
            }

            @Override
            public void onError(Throwable t) {
                LaunchMenu.getInstance().getLogger().info(t.getMessage());
                if(player != null){
                    player.sendMessage("§cНе удалось создать сервер");
                }
                LaunchMenu.getInstance().getLogger().info("Server not created");
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    public void DeleteServer(int port){
        ServerOuterClass.DeleteServerRequest request = ServerOuterClass.DeleteServerRequest
                .newBuilder()
                .setPort(port)
                .buildPartial();
        this.stub.deleteServer(request, new DeleteCallback());
    }

    class DeleteCallback implements StreamObserver<ServerOuterClass.Response>{

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
    public void ListServers(){
        stub.listServers(ServerOuterClass.Empty.newBuilder().build(), new StreamObserver<ServerOuterClass.ListServersResponse>() {
            @Override
            public void onNext(ServerOuterClass.ListServersResponse value) {
                LaunchMenu.getInstance().setServers(value.getServersList());
                LaunchMenu.getInstance().getLogger().info("Servers: " + value.getServersList().size());
            }

            @Override
            public void onError(Throwable t) {
                LaunchMenu.getInstance().getLogger().severe("Error getting servers");
            }

            @Override
            public void onCompleted() {
                LaunchMenu.getInstance().getLogger().info("Servers received");
            }
        });
    }

    public void ListPLugins(){
        stub.getPlugins(ServerOuterClass.Empty.newBuilder().build(), new StreamObserver<ServerOuterClass.Plugins>() {
            @Override
            public void onNext(ServerOuterClass.Plugins value) {
                LaunchMenu.getInstance().setPlugins(value.getPluginsList());
                LaunchMenu.getInstance().getLogger().info("Plugins: " + value.getPluginsList().size());
            }

            @Override
            public void onError(Throwable t) {
                LaunchMenu.getInstance().getLogger().severe("Error getting plugins");
            }

            @Override
            public void onCompleted() {
                LaunchMenu.getInstance().getLogger().info("Plugins received");
            }
        });
    }

    public void ListVersions(){
        stub.getVersions(ServerOuterClass.Empty.newBuilder().build(), new StreamObserver<ServerOuterClass.Versions>() {
            @Override
            public void onNext(ServerOuterClass.Versions value) {
                LaunchMenu.getInstance().getLogger().info("Versions: " + value.getVersionsList().size());
                LaunchMenu.getInstance().setVersions(value.getVersionsList());
            }

            @Override
            public void onError(Throwable t) {
                LaunchMenu.getInstance().getLogger().severe("Error getting versions");
            }

            @Override
            public void onCompleted() {
                LaunchMenu.getInstance().getLogger().info("Versions received");
            }
        });
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
