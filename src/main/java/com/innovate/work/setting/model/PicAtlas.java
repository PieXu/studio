package com.innovate.work.setting.model;

import com.innovate.basic.annotation.Invisible;
import com.innovate.basic.base.BaseModel;

/**
 * 图集信息
 * 
 * @author IvanHsu
 * @2018年5月2日 下午2:32:34
 */
public class PicAtlas extends BaseModel {

	@Invisible
	public static final String TAB_NAME = "t_pic_atlas";
	@Invisible
	private static final long serialVersionUID = 4603657347507964343L;
	private String atlasName;
	private String atlasCode;
	private int picNum;

	public String getAtlasName() {
		return atlasName;
	}

	public void setAtlasName(String atlasName) {
		this.atlasName = atlasName;
	}

	public String getAtlasCode() {
		return atlasCode;
	}

	public void setAtlasCode(String atlasCode) {
		this.atlasCode = atlasCode;
	}

	public int getPicNum() {
		return picNum;
	}

	public void setPicNum(int picNum) {
		this.picNum = picNum;
	}

}
