//package com.hjr.mq;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Component;
//
//import java.io.UnsupportedEncodingException;
//
//@Component
//public class ClazzReceiver {
//	@RabbitListener(queues = "hjr-saas")
//	public void test1(@Payload byte[] text) throws UnsupportedEncodingException {
//		String message = new String(text, "UTF-8");
//		System.out.println(message);
//	}
//}
