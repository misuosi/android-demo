/*
 * Copyright (c) 2014 Myth Technology, Inc. All rights reserved.
 */
package com.mythos.demo.example.mina;

import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * Description		: mina客户端启动测试类，本类写在服务器端做测试用
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
public class MinaClientTest{
	
	public static void main(String[] args) {

        // 创建一个基于NIO的tcp/ip连接
        NioSocketConnector connector = new NioSocketConnector();
		// 创建接收数据的过滤器
        DefaultIoFilterChainBuilder chain = connector.getFilterChain();
        // 设定过滤器一行行(/r/n)的读取字符串数据
        //chain.addLast("StringFilter", new ProtocolCodecFilter(new TextLineCodecFactory()   ));
        // 设定这个过滤器将以对象为单位读取数据
        ProtocolCodecFilter filter = new ProtocolCodecFilter(
                new ObjectSerializationCodecFactory());
        // 设定服务器端的消息处理器:一个SamplMinaServerHandler对象,
        chain.addLast("objectFilter",filter);
 
        // 设定服务器端的消息处理器:一个 SamplMinaServerHandler 对象,
        connector.setHandler(new MinaClientHanlder());
        // 设置超时时间.
        connector.setConnectTimeoutCheckInterval(30);
        // 连接到服务器:
        final ConnectFuture cf = connector.connect(new InetSocketAddress("localhost",
                MinaServer.PORT));
        /*JFrame jFrame = new JFrame("test");
        JButton btn = new JButton();
		btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Map<String, Object> msg = new HashMap<String, Object>();
				msg.put("message", "hello");
				cf.getSession().write(msg);
			}
			
		});
		jFrame.add(btn);

		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭按钮的动作为退出窗口  
		jFrame.setBounds(400, 300, 400, 300); 
		jFrame.setVisible(true);*/
        // 等待服务器相应，直到连接断开
        cf.awaitUninterruptibly();
        cf.getSession().getCloseFuture().awaitUninterruptibly();
        // 释放资源
        //connector.dispose();
        
	}

}
