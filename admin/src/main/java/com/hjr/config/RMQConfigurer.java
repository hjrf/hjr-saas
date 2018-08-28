///**
// *
// */
//package com.hjr.config;
//
//import org.springframework.amqp.rabbit.annotation.EnableRabbit;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@EnableRabbit
//public class RMQConfigurer {
//
//	@Value("${spring.rabbitmq.addresses}")
//	private String addresses;
//	@Value("${spring.rabbitmq.username}")
//	private String username;
//	@Value("${spring.rabbitmq.password}")
//	private String password;
//	@Value("${spring.rabbitmq.virtual-host}")
//	private String virtualHost;
//	@Value("${spring.rabbitmq.publisher-confirms}")
//	private boolean publisherConfirms;
//
//	@Bean
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setAddresses(addresses);
//        connectionFactory.setUsername(username);
//        connectionFactory.setPassword(password);
//        connectionFactory.setVirtualHost(virtualHost);
//        connectionFactory.setPublisherConfirms(publisherConfirms);
//        return connectionFactory;
//    }
//}
