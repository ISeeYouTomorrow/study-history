package com.test.concurrent.study.netty.chapter1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

import javax.net.ssl.SSLException;

/**
 * @ClassName EchoClient
 * @Author @lvxile
 * @Date 2019/8/23 14:02
 * @Description TODO
 */
public class EchoClient {
    static final boolean SSL = System.getProperty("ssl") != null;
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8000"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));

    public static void main(String[] args) throws SSLException {
        final SslContext sslContext;
        if (SSL) {
            sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        }else {
            sslContext = null;
        }

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bs = new Bootstrap();
            bs.group(group)
              .channel(NioSocketChannel.class)
              .option(ChannelOption.TCP_NODELAY, true)
              .handler(new ChannelInitializer<SocketChannel>() {
                  @Override
                  protected void initChannel(SocketChannel ch) throws Exception {
                      ChannelPipeline cp = ch.pipeline();
                      if (sslContext != null) {
                          cp.addLast(sslContext.newHandler(ch.alloc(), HOST, SIZE));
                      }
                      cp.addLast(new EchoClinetHandler());
                  }
              });

            ChannelFuture ch = bs.connect(HOST, PORT).sync();
            ch.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }

}
