package com.itlemon.study.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

	private int port;

	public NettyServer(int port) {
		this.port = port;
	}
	
	public void run() throws Exception {
		
		//接受工作线程(接收进来的连接)
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		
		//处理工作线程(用来处理已经被接收的连接)
		EventLoopGroup workGroup = new NioEventLoopGroup();
		
		try{ 
			//初始化启动
			ServerBootstrap sbp = new ServerBootstrap();
			
			sbp.group(bossGroup, workGroup);//绑定共组线程组
			
			sbp.channel(NioServerSocketChannel.class);
			
			//处理连接数
			sbp.option(ChannelOption.SO_BACKLOG,1);
			
			//开启keepalive
			sbp.childOption(ChannelOption.SO_KEEPALIVE, true);
			
			//
			sbp.childHandler(new ChildChannelHandler());
			
			ChannelFuture fu = sbp.bind(this.port).sync();
			
			fu.channel().closeFuture().sync();
			
		}finally{
			workGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) {
		try {
			System.out.println("服务端开启等待客户端链接");
			new NettyServer(12345).run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
