/*
 * Copyright (c) 2014 Myth Technology, Inc. All rights reserved.
 */
package com.mythos.demo.example.mina;

import java.util.Map;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * Description		: 客户端IoHandler
 * 
 * 
 * <br><br>Time		: 2015-2-6  下午7:48:21
 * 
 * @version 1.0
 * 
 * @since 1.0
 * 
 * @author YLM
 */
public class MinaClientHanlder extends IoHandlerAdapter {
	 
	    // 当一个客端端连结进入时 @Override
	    public void sessionOpened(IoSession session) throws Exception {
	        System.out.println("我 登陆了！服务器地址： : "
	                + session.getRemoteAddress());
	    }
	 
	    // 当客户端发送的消息到达时:
	    @Override
	    public void messageReceived(IoSession session, Object message)
	            throws Exception {
	    	Map<String, Object> map = (Map<String, Object>)message;
	        System.out.println(map.get("reply"));
	 
	    }
	 
	    // 当信息已经传送给服务器后触发此方法.
	    @Override
	    public void messageSent(IoSession session, Object message) {
	        System.out.println("信息已经发送给服务器");
	 
	    }
	 
	    // 当一个客户端关闭时
	    @Override
	    public void sessionClosed(IoSession session) {
	        System.out.println("本客户端断开连接");
	    }
	 
	    // 当连接空闲时触发此方法.
	    @Override
	    public void sessionIdle(IoSession session, IdleStatus status) {
	        System.out.println("连接空闲");
	    }
	 
	    // 当接口中其他方法抛出异常未被捕获时触发此方法
	    @Override
	    public void exceptionCaught(IoSession session, Throwable cause) {
	        System.out.println("其他方法抛出异常");
	    }
}
