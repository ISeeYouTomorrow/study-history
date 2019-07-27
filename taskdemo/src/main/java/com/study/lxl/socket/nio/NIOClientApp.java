package com.study.lxl.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName NIOClientApp
 * @Author @lvxile
 * @Date 2019/7/25 19:24
 * @Description TODO
 */
public class NIOClientApp {

    public void start() {
        Thread t = new Thread(new TimeClientHandle("localhost", 8008));
        t.start();
    }

    public static void main(String[] args) {
        new NIOClientApp().start();
    }

    class TimeClientHandle implements Runnable {

        private String host;
        private int port;
        private Selector selector;
        private SocketChannel socketChannel;
        private boolean stop;


        TimeClientHandle(String host, int port) {
            this.host = host;
            this.port = port;

            try {
                socketChannel = SocketChannel.open();
                //设置为异步非阻塞模式，同时还设置SockeChannel的tcp参数
                socketChannel.configureBlocking(false);
                selector = Selector.open();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        @Override
        public void run() {
            try {
                doConnect();
            } catch (IOException e) {
                e.printStackTrace();
            }

            while (!stop) {
                try {
                    selector.select(1000);
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> it = keys.iterator();
                    SelectionKey key ;
                    while (it.hasNext()) {//轮询多路复用器Selector，当有就绪的Channel时
                        key = it.next();
                        it.remove();
                        try {
                            handleInput(key);
                        } catch (Exception e) {
                            if(key!=null){
                                key.cancel();
                                if(key.channel()!=null){
                                    key.channel().close();
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                }

            }

        }

        private void handleInput(SelectionKey key) throws ClosedChannelException, IOException {
            if(key.isValid()){
                //判断是否连接成功
                SocketChannel sc=(SocketChannel) key.channel();
                if(key.isConnectable()){//处于连接状态，说明服务器已经返回ACK应答消息
                    if(sc.finishConnect()){//对连接结果进行判断
                        /*
                         * 将SocketChannel注册到多路复用器上，注册SelectionKey.OP_READ操作位，
                         * 监听网络读操作，然后发送请求消息给服务端。
                         */
                        sc.register(selector, SelectionKey.OP_READ);
                        doWrite(sc);
                    }else{
                        System.exit(1);//连接失败，进程退出
                    }
                }
                if(key.isReadable()){
                    //开辟缓冲区
                    ByteBuffer readBuffer=ByteBuffer.allocate(1024);
                    //异步读取
                    int readBytes=sc.read(readBuffer);
                    if(readBytes>0){
                        readBuffer.flip();
                        byte[] bytes=new byte[readBuffer.remaining()];
                        readBuffer.get(bytes);
                        String body=new String(bytes, "UTF-8");
                        System.out.println("Now is: "+body);
                        this.stop=true;
                    }else if(readBytes<0){
                        //对端链路关闭
                        key.cancel();
                        sc.close();
                    }else{
                        //读到0字节，忽略
                    }
                }
            }
        }
        private void doConnect() throws IOException {
            //如果直接连接成功，则将SocketChannel注册到多路复用器Selector上，发送请求消息，读应答
            if(socketChannel.connect(new InetSocketAddress(host, port))){
                socketChannel.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE);
                doWrite(socketChannel);
            }else{
                /*
                 * 如果没有直接连接成功，则说明服务端没有返回TCP握手应答信息，但这并不代表连接失败，
                 * 我们需要将SocketChannel注册到多路复用器Selector上，注册SelectionKey.OP_CONNECT，
                 * 当服务端返回TCP syn-ack消息后，Selector就能轮询到整个SocketChannel处于连接就绪状态。
                 */
                socketChannel.register(selector, SelectionKey.OP_CONNECT);
            }
        }

        private void doWrite(SocketChannel sc) throws IOException {
            byte[] req="QUERY TIME ORDER".getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
            //写入到发送缓冲区中
            writeBuffer.put(req);
            writeBuffer.flip();
            //由于发送是异步的，所以会存在"半包写"问题，此处不赘述
            sc.write(writeBuffer);
            if(!writeBuffer.hasRemaining()){//如果缓冲区中的消息全部发送完成
                System.out.println("Send order 2 server succeed.");
            }
        }
    }

}
