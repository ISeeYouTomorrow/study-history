package com.test.concurrent.study.netty.chapter1;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;

/**
 * @ClassName EchoServer
 * @Author @lvxile
 * @Date 2019/8/23 13:15
 * @Description TODO
 */
public class EchoServer {

    static final boolean SSL = System.getProperty("ssl")!=null;
    static final int PORT = Integer.parseInt(System.getProperty("port", "8000"));

    public static void main(String[] args) throws CertificateException, SSLException {
        final SslContext sslContext;
        if (SSL) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslContext = SslContextBuilder.forServer(ssc.certificate(),ssc.privateKey())
                    .build();
        }else {
            sslContext = null;
        }

        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        final EchoServerHandler serverHandler = new EchoServerHandler();
        try {
            ServerBootstrap bs = new ServerBootstrap();
            bs.group(boss,worker)
              .channel(NioServerSocketChannel.class)
              .option(ChannelOption.SO_BACKLOG, 100)
              .handler(new LoggingHandler(LogLevel.INFO))
              .childHandler(new ChannelInitializer<SocketChannel>() {
                  @Override
                  protected void initChannel(SocketChannel ch) {
                      ChannelPipeline cp = ch.pipeline();
                      if (sslContext != null) {
                          cp.addLast(sslContext.newHandler(ch.alloc()));
                      }

                      cp.addLast(serverHandler);
                  }
              });

            // start server
            ChannelFuture future = bs.bind(PORT).sync();
            // 等待直到服务器socket关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            // Shut down all event loops to terminate all threads.
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }


}
