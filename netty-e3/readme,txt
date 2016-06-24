基于Netty5.0入门案例三之NettyServer字符串解码器

1、在netty中能否有自动的把接收的Bytebuf数据转String，不需要我手动处理？
        答：有，可以在管道中添加一个StringDecoder。
        
2、在网络传输过程中有半包粘包的问题，netty能解决吗？
        答：能，netty提供了很丰富的解码器，在正确合理的使用下就能解决半包粘包问题。
        
3、常用的String字符串下有什么样的解码器呢？
        答：不仅在String下有处理半包粘包的解码器在处理其他的数据格式也有，其中谷歌的protobuf数据格式就是其中一个。对于String的有一下常用的三种：
	  1、LineBasedFrameDecoder            基于换行
	  2、DelimiterBasedFrameDecoder      基于指定字符串
	  3、FixedLengthFrameDecoder         基于字符串长度
	  
本文摘自:http://www.itstack.org/?post=7