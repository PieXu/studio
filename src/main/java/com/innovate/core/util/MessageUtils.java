package com.innovate.core.util;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageUtils {
	// 消息模板
	private static SimpMessagingTemplate messageTemplate;
	@Autowired
	private SimpMessagingTemplate messageTemplate2;

	@PostConstruct
	public void init() {
		messageTemplate = this.messageTemplate2;
	}

	/**
	 * 点对点发送消息
	 * 
	 * @param userId
	 * @param destination
	 * @param message
	 *            void 2017年11月13日
	 */
	public static void sendMessage2User(String userKey, String destination, TextMessage message) {
		if (StringUtils.isNotBlank(userKey)) {
			messageTemplate.convertAndSendToUser(userKey.toString(), destination, message);
		}
	}

	/**
	 * 广播地址消息
	 * 
	 * @param destination
	 * @param message
	 *            void 2017年11月13日
	 */
	public static void sendMessage2All(String destination, TextMessage message) {
		messageTemplate.convertAndSend(destination, message);
	}

	/**
	 * 消息协议头
	 * 
	 * @param sessionId
	 * @return MessageHeaders 2017年11月13日
	 */
	public static MessageHeaders createHeaders(String sessionId) {
		SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
		headerAccessor.setSessionId(sessionId);
		headerAccessor.setLeaveMutable(true);
		return headerAccessor.getMessageHeaders();
	}

}
