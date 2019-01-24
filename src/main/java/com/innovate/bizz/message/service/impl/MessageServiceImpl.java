package com.innovate.bizz.message.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.innovate.bizz.message.dao.MessageDao;
import com.innovate.bizz.message.model.Message;
import com.innovate.bizz.message.service.IMessageService;

@Service
public class MessageServiceImpl implements IMessageService{

	@Autowired
	private MessageDao messageDao;

	@Override
	public Page<Message> listMessage(Message message) {
		return messageDao.listMessage(message);
	}

	@Override
	public void deleteById(String id) {
		if(StringUtils.isNotBlank(id)){
			messageDao.deleteById(id);
		}
	}

	@Override
	public Message getById(String id) {
		if(StringUtils.isNotBlank(id)){
			return messageDao.getMessageById(id);
		}
		return null;
	}

	@Override
	public void updateMessage(Message message) {
		if(null!=message){
			String id = message.getId();
			if(StringUtils.isNotBlank(id)){
				messageDao.updateMessage(message);
			}
		}
	}

	@Override
	public void saveMessage(Message message) {
		if(null!=message){
			messageDao.saveMessage(message);
		}
		
	}
	
}
