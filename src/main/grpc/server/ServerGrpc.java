package server;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.47.0)",
    comments = "Source: server.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ServerGrpc {

  private ServerGrpc() {}

  public static final String SERVICE_NAME = "server.Server";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<server.ServerOuterClass.CreateServerRequest,
      server.ServerOuterClass.Response> getCreateServerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateServer",
      requestType = server.ServerOuterClass.CreateServerRequest.class,
      responseType = server.ServerOuterClass.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<server.ServerOuterClass.CreateServerRequest,
      server.ServerOuterClass.Response> getCreateServerMethod() {
    io.grpc.MethodDescriptor<server.ServerOuterClass.CreateServerRequest, server.ServerOuterClass.Response> getCreateServerMethod;
    if ((getCreateServerMethod = ServerGrpc.getCreateServerMethod) == null) {
      synchronized (ServerGrpc.class) {
        if ((getCreateServerMethod = ServerGrpc.getCreateServerMethod) == null) {
          ServerGrpc.getCreateServerMethod = getCreateServerMethod =
              io.grpc.MethodDescriptor.<server.ServerOuterClass.CreateServerRequest, server.ServerOuterClass.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateServer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.ServerOuterClass.CreateServerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.ServerOuterClass.Response.getDefaultInstance()))
              .setSchemaDescriptor(new ServerMethodDescriptorSupplier("CreateServer"))
              .build();
        }
      }
    }
    return getCreateServerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<server.ServerOuterClass.UpdateServerRequest,
      server.ServerOuterClass.Response> getUpdateServerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateServer",
      requestType = server.ServerOuterClass.UpdateServerRequest.class,
      responseType = server.ServerOuterClass.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<server.ServerOuterClass.UpdateServerRequest,
      server.ServerOuterClass.Response> getUpdateServerMethod() {
    io.grpc.MethodDescriptor<server.ServerOuterClass.UpdateServerRequest, server.ServerOuterClass.Response> getUpdateServerMethod;
    if ((getUpdateServerMethod = ServerGrpc.getUpdateServerMethod) == null) {
      synchronized (ServerGrpc.class) {
        if ((getUpdateServerMethod = ServerGrpc.getUpdateServerMethod) == null) {
          ServerGrpc.getUpdateServerMethod = getUpdateServerMethod =
              io.grpc.MethodDescriptor.<server.ServerOuterClass.UpdateServerRequest, server.ServerOuterClass.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateServer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.ServerOuterClass.UpdateServerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.ServerOuterClass.Response.getDefaultInstance()))
              .setSchemaDescriptor(new ServerMethodDescriptorSupplier("UpdateServer"))
              .build();
        }
      }
    }
    return getUpdateServerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<server.ServerOuterClass.DeleteServerRequest,
      server.ServerOuterClass.Response> getDeleteServerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteServer",
      requestType = server.ServerOuterClass.DeleteServerRequest.class,
      responseType = server.ServerOuterClass.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<server.ServerOuterClass.DeleteServerRequest,
      server.ServerOuterClass.Response> getDeleteServerMethod() {
    io.grpc.MethodDescriptor<server.ServerOuterClass.DeleteServerRequest, server.ServerOuterClass.Response> getDeleteServerMethod;
    if ((getDeleteServerMethod = ServerGrpc.getDeleteServerMethod) == null) {
      synchronized (ServerGrpc.class) {
        if ((getDeleteServerMethod = ServerGrpc.getDeleteServerMethod) == null) {
          ServerGrpc.getDeleteServerMethod = getDeleteServerMethod =
              io.grpc.MethodDescriptor.<server.ServerOuterClass.DeleteServerRequest, server.ServerOuterClass.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteServer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.ServerOuterClass.DeleteServerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.ServerOuterClass.Response.getDefaultInstance()))
              .setSchemaDescriptor(new ServerMethodDescriptorSupplier("DeleteServer"))
              .build();
        }
      }
    }
    return getDeleteServerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<server.ServerOuterClass.Empty,
      server.ServerOuterClass.ListServersResponse> getListServersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListServers",
      requestType = server.ServerOuterClass.Empty.class,
      responseType = server.ServerOuterClass.ListServersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<server.ServerOuterClass.Empty,
      server.ServerOuterClass.ListServersResponse> getListServersMethod() {
    io.grpc.MethodDescriptor<server.ServerOuterClass.Empty, server.ServerOuterClass.ListServersResponse> getListServersMethod;
    if ((getListServersMethod = ServerGrpc.getListServersMethod) == null) {
      synchronized (ServerGrpc.class) {
        if ((getListServersMethod = ServerGrpc.getListServersMethod) == null) {
          ServerGrpc.getListServersMethod = getListServersMethod =
              io.grpc.MethodDescriptor.<server.ServerOuterClass.Empty, server.ServerOuterClass.ListServersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListServers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.ServerOuterClass.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.ServerOuterClass.ListServersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServerMethodDescriptorSupplier("ListServers"))
              .build();
        }
      }
    }
    return getListServersMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ServerStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServerStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServerStub>() {
        @java.lang.Override
        public ServerStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServerStub(channel, callOptions);
        }
      };
    return ServerStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ServerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServerBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServerBlockingStub>() {
        @java.lang.Override
        public ServerBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServerBlockingStub(channel, callOptions);
        }
      };
    return ServerBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ServerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServerFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServerFutureStub>() {
        @java.lang.Override
        public ServerFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServerFutureStub(channel, callOptions);
        }
      };
    return ServerFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ServerImplBase implements io.grpc.BindableService {

    /**
     */
    public void createServer(server.ServerOuterClass.CreateServerRequest request,
        io.grpc.stub.StreamObserver<server.ServerOuterClass.Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateServerMethod(), responseObserver);
    }

    /**
     */
    public void updateServer(server.ServerOuterClass.UpdateServerRequest request,
        io.grpc.stub.StreamObserver<server.ServerOuterClass.Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateServerMethod(), responseObserver);
    }

    /**
     */
    public void deleteServer(server.ServerOuterClass.DeleteServerRequest request,
        io.grpc.stub.StreamObserver<server.ServerOuterClass.Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteServerMethod(), responseObserver);
    }

    /**
     */
    public void listServers(server.ServerOuterClass.Empty request,
        io.grpc.stub.StreamObserver<server.ServerOuterClass.ListServersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListServersMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateServerMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                server.ServerOuterClass.CreateServerRequest,
                server.ServerOuterClass.Response>(
                  this, METHODID_CREATE_SERVER)))
          .addMethod(
            getUpdateServerMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                server.ServerOuterClass.UpdateServerRequest,
                server.ServerOuterClass.Response>(
                  this, METHODID_UPDATE_SERVER)))
          .addMethod(
            getDeleteServerMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                server.ServerOuterClass.DeleteServerRequest,
                server.ServerOuterClass.Response>(
                  this, METHODID_DELETE_SERVER)))
          .addMethod(
            getListServersMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                server.ServerOuterClass.Empty,
                server.ServerOuterClass.ListServersResponse>(
                  this, METHODID_LIST_SERVERS)))
          .build();
    }
  }

  /**
   */
  public static final class ServerStub extends io.grpc.stub.AbstractAsyncStub<ServerStub> {
    private ServerStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServerStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServerStub(channel, callOptions);
    }

    /**
     */
    public void createServer(server.ServerOuterClass.CreateServerRequest request,
        io.grpc.stub.StreamObserver<server.ServerOuterClass.Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateServerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateServer(server.ServerOuterClass.UpdateServerRequest request,
        io.grpc.stub.StreamObserver<server.ServerOuterClass.Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateServerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteServer(server.ServerOuterClass.DeleteServerRequest request,
        io.grpc.stub.StreamObserver<server.ServerOuterClass.Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteServerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listServers(server.ServerOuterClass.Empty request,
        io.grpc.stub.StreamObserver<server.ServerOuterClass.ListServersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListServersMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ServerBlockingStub extends io.grpc.stub.AbstractBlockingStub<ServerBlockingStub> {
    private ServerBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServerBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServerBlockingStub(channel, callOptions);
    }

    /**
     */
    public server.ServerOuterClass.Response createServer(server.ServerOuterClass.CreateServerRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateServerMethod(), getCallOptions(), request);
    }

    /**
     */
    public server.ServerOuterClass.Response updateServer(server.ServerOuterClass.UpdateServerRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateServerMethod(), getCallOptions(), request);
    }

    /**
     */
    public server.ServerOuterClass.Response deleteServer(server.ServerOuterClass.DeleteServerRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteServerMethod(), getCallOptions(), request);
    }

    /**
     */
    public server.ServerOuterClass.ListServersResponse listServers(server.ServerOuterClass.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListServersMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ServerFutureStub extends io.grpc.stub.AbstractFutureStub<ServerFutureStub> {
    private ServerFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServerFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<server.ServerOuterClass.Response> createServer(
        server.ServerOuterClass.CreateServerRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateServerMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<server.ServerOuterClass.Response> updateServer(
        server.ServerOuterClass.UpdateServerRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateServerMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<server.ServerOuterClass.Response> deleteServer(
        server.ServerOuterClass.DeleteServerRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteServerMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<server.ServerOuterClass.ListServersResponse> listServers(
        server.ServerOuterClass.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListServersMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_SERVER = 0;
  private static final int METHODID_UPDATE_SERVER = 1;
  private static final int METHODID_DELETE_SERVER = 2;
  private static final int METHODID_LIST_SERVERS = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ServerImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ServerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_SERVER:
          serviceImpl.createServer((server.ServerOuterClass.CreateServerRequest) request,
              (io.grpc.stub.StreamObserver<server.ServerOuterClass.Response>) responseObserver);
          break;
        case METHODID_UPDATE_SERVER:
          serviceImpl.updateServer((server.ServerOuterClass.UpdateServerRequest) request,
              (io.grpc.stub.StreamObserver<server.ServerOuterClass.Response>) responseObserver);
          break;
        case METHODID_DELETE_SERVER:
          serviceImpl.deleteServer((server.ServerOuterClass.DeleteServerRequest) request,
              (io.grpc.stub.StreamObserver<server.ServerOuterClass.Response>) responseObserver);
          break;
        case METHODID_LIST_SERVERS:
          serviceImpl.listServers((server.ServerOuterClass.Empty) request,
              (io.grpc.stub.StreamObserver<server.ServerOuterClass.ListServersResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ServerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ServerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return server.ServerOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Server");
    }
  }

  private static final class ServerFileDescriptorSupplier
      extends ServerBaseDescriptorSupplier {
    ServerFileDescriptorSupplier() {}
  }

  private static final class ServerMethodDescriptorSupplier
      extends ServerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ServerMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ServerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ServerFileDescriptorSupplier())
              .addMethod(getCreateServerMethod())
              .addMethod(getUpdateServerMethod())
              .addMethod(getDeleteServerMethod())
              .addMethod(getListServersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
