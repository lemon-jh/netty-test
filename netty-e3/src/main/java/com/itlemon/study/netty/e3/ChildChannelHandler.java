package com.itlemon.study.netty.e3;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

	@Override   
	protected void initChannel(SocketChannel ch) throws Exception {
		System.out.println("==>报告：有一客户端链接到本服务端");
		System.out.println("==>ip"+ ch.remoteAddress().getHostName());
		System.out.println("报告完毕");
		
		ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
		
		// 基于指定字符串【换行符，这样功能等同于LineBasedFrameDecoder】
//		ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, false, Delimiters.lineDelimiter()));
		
		// 基于最大长度
//		ch.pipeline().addLast(new FixedLengthFrameDecoder(4));
		
		ch.pipeline().addLast(new StringDecoder());
		
		ch.pipeline().addLast(new MyServcrHandler());
	}

}
