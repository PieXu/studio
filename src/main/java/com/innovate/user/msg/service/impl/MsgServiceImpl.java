package com.innovate.user.msg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovate.user.msg.dao.MsgDao;
import com.innovate.user.msg.service.IMsgService;

@Service
public class MsgServiceImpl implements IMsgService{

	@Autowired
	private MsgDao msgDao;
}
