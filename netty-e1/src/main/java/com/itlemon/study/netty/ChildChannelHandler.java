package com.itlemon.study.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		System.out.println("==>报告");
		System.out.println("==>信息：有一客户端链接到本服务端");
		System.out.println("==>metadata："+ch.metadata());
		System.out.println("==>ip："+ch.localAddress().getHostName());
		System.out.println("==>ip："+ch.localAddress().getPort());
		System.out.println("报告完毕");
	}
}
