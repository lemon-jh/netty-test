package com.itlemon.study.netty.e3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

	private int port ;

	public NettyServer(int port) {
		super();
		this.port = port;
	} 
	
	public void run() throws Exception {
		
		EventLoopGroup boss = new NioEventLoopGroup();
		
		EventLoopGroup work = new NioEventLoopGroup();
		
		try{
			
			ServerBootstrap sbp = new ServerBootstrap();
			sbp.group(boss, work).channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, 1)
			.childOption(ChannelOption.SO_KEEPALIVE, true)
			.childHandler(new ChildChannelHandler());
			
			ChannelFuture f = sbp.bind(this.port).sync();
			
			f.channel().closeFuture().sync();
			
		}finally{
			work.shutdownGracefully();
			boss.shutdownGracefully();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			System.out.println("==>服务端开启等待客户端链接");
			new NettyServer(12345).run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
