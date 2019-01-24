package com.innovate.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;


/**
 * WebScoket配置
 * 
 * @author IvanHsu
 * @2018年4月2日 下午4:59:43
 */
@Configuration
@EnableWebMvc
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic","/user");  
       /* config.setApplicationDestinationPrefixes("/app");  
        config.setUserDestinationPrefix("/user/");  */
		// 应用程序以 /app 为前缀，而 代理目的地以 /topic 为前缀.
		// js.url = "/spring13/app/hello" -> @MessageMapping("/hello") 注释的方法.
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/webServer").withSockJS();  
		// 在网页上我们就可以通过这个链接 /server/hello 来和服务器的WebSocket连接
	}

}
