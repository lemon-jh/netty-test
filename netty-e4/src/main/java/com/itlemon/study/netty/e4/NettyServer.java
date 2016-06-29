package com.itlemon.study.netty.e4;

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
	
	public void run () throws Exception{
		
		EventLoopGroup boss = new NioEventLoopGroup();
		
		EventLoopGroup work = new NioEventLoopGroup();
		
		try{

			ServerBootstrap sbp = new ServerBootstrap();
			
			sbp.group(boss, work);
			
			sbp.channel(NioServerSocketChannel.class);
			
			sbp.childHandler(new ChildChannelHandler());
			
			sbp.option(ChannelOption.SO_BACKLOG,128);
			
			sbp.childOption(ChannelOption.SO_KEEPALIVE,true);
			
			ChannelFuture f = sbp.bind(this.port).sync();
			
			f.channel().closeFuture().sync();
			
		}finally{
			work.shutdownGracefully();
			boss.shutdownGracefully();
		}
	}
	
	
	public static void main(String[] args) {
		try {
			new NettyServer(12345).run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
