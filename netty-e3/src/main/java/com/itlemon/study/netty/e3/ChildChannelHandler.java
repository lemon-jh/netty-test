package com.itlemon.study.netty.e3;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		System.out.println("==>报告：有一客户端链接到本服务端");
		System.out.println("==>ip"+ ch.remoteAddress().getHostName());
		System.out.println("报告完毕");
		ch.pipeline().addLast(new MyServcrHandler());
	}

}
