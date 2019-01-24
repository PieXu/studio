package com.innovate.bizz.message.service;

import com.github.pagehelper.Page;
import com.innovate.basic.base.IBaseService;
import com.innovate.bizz.message.model.Message;

/**
 * 通知提示业务
 * @author IvanHsu
 * @2018年7月7日 上午9:58:55
 */
public interface IMessageService extends IBaseService{

	/**
	 * 按条件查找
	 * @param message
	 * @return
	 */
	public Page<Message> listMessage(Message message);

	/**
	 * 按主键删除
	 * @param id
	 */
	public void deleteById(String id);

	/**
	 * 按主键查找
	 * @param id
	 * @return
	 */
	public Message getById(String id);

	/**
	 * 更新
	 * @param message
	 */
	public void updateMessage(Message message);

	/**
	 * 保存
	 * @param message
	 */
	public void saveMessage(Message message);
	
}
