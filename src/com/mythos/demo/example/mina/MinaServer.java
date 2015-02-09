/*
 * Copyright (c) 2014 Myth Technology, Inc. All rights reserved.
 */
package com.mythos.demo.example.mina;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.mythos.demo.common.config.HttpConfig;

/**
 * Description		: mina服务器启动类，本类写在服务器端运行
 * 
 * 
 * <br><br>Time		: 2015-2-6  下午7:42:26
 * 
 * @version 1.0
 * 
 * @since 1.0
 * 
 * @author YLM
 */
public class MinaServer {
	
	public static void main(String[] args) {
		//创建一个非阻塞的server端Socket ，用NIO
        SocketAcceptor acceptor = new NioSocketAcceptor();
 
        /*---------接收字符串---------*/
        //创建一个接收数据过滤器
        DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
        //设定过滤器一行行(/r/n)的读取数据
        chain.addLast("StringFilter", new ProtocolCodecFilter(new TextLineCodecFactory()));
        /*---------接收对象---------*/
        //设定这个过滤器将以对象为单位读取数据
        //ProtocolCodecFilter filter= new ProtocolCodecFilter(new ObjectSerializationCodecFactory());
        //chain.addLast("objectFilter",filter);
 
        //设定服务器消息处理器
        acceptor.setHandler(new MinaServerHanlder());
        //绑定端口，启动服务器
        try {
            acceptor.bind(new InetSocketAddress(HttpConfig.MINA_PORT));
        } catch (IOException e) {
            System.out.println("Mina Server start for error!");
            e.printStackTrace();
        }
        System.out.println("Mina Server run done! on port:"+ HttpConfig.MINA_PORT);
	}

}
