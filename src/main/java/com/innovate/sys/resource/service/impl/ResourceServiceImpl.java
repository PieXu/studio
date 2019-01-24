package com.innovate.sys.resource.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovate.sys.resource.dao.ResourceDao;
import com.innovate.sys.resource.model.Resource;
import com.innovate.sys.resource.service.IResourceService;
import com.innovate.util.IdUtil;

@Service("sys.service.ResourceServiceImpl")
public class ResourceServiceImpl implements IResourceService{
	
	@Autowired
	private ResourceDao resDao;

	@Override
	public List<Resource> getAllRootResource() {
		return resDao.getAllRootResource();
	}

	@Override
	public List<Resource> getResourceByParent(String parent) {
		if(StringUtils.isNotBlank(parent)){
			return resDao.getResourceByParent(parent);
		}
		return null;
	}

	@Override
	public Resource getResourceById(String id) {
		if(StringUtils.isNotBlank(id)){
			return resDao.getResourceById(id);
		}
		return null;
	}

	@Override
	public void saveOrUpdateResource(Resource res) {
		String id = res.getId();
		if(StringUtils.isNotBlank(id)){
			res.setUpdateTime(new Date());
			resDao.updateResource(res);
		}else{
			res.setId(IdUtil.getUUID());
			Integer maxSeq = resDao.getMaxSeqByParent(res.getParent());
			int seq = null == maxSeq ? 1 : maxSeq+1;
			res.setSeqNum(seq);
			res.setCreateTime(new Date());
			res.setUpdateTime(new Date());
			resDao.saveResource(res);
		}
	}

	@Override
	public void delResource(String id) {
		if(StringUtils.isNotBlank(id)){
			resDao.delResource(id);
		}
	}

	@Override
	public int checkHasChild(String parent) {
		if(StringUtils.isNotBlank(parent)){
			return resDao.checkHasChild(parent);
		}
		return 0;
	}
	
	/**
	 * 下移菜单
	 */
	@Override
	public void dowResSeq(String id, String parent, Integer seq) {
		if(null!=seq){
			int nextSeq = seq+1;
			Resource nextRes = resDao.getResourceBySeq(parent,nextSeq);
			if(null!=nextRes){
				nextRes.setSeqNum(seq);
				Resource curentRes = new Resource();
				curentRes.setId(id);
				curentRes.setSeqNum(nextSeq);
				resDao.updateResource(nextRes);
				resDao.updateResource(curentRes);
			}
		}
	}

	/**
	 * 上移菜单
	 */
	@Override
	public void upResSeq(String id, String parent, Integer seq) {
		if(null!=seq && seq > 1){
			int preSeq = seq-1;
			Resource preRes = resDao.getResourceBySeq(parent,preSeq);
			if(null!=preRes){
				preRes.setSeqNum(seq);
				Resource curentRes = new Resource();
				curentRes.setId(id);
				curentRes.setSeqNum(preSeq);
				resDao.updateResource(preRes);
				resDao.updateResource(curentRes);
			}
		}
		
	}

	/**
	 * 查找权限下的菜单的信息
	 */
	@Override
//	@Cacheable(value={"sys.service.ResourceServiceImpl"}, key="#currentUserId+'_'+#parent + 'getPermissionResourceByParent'")
	public List<Resource> getPermissionResourceByParent(String parent, String currentUserId) {
		return resDao.getPermissionResourceByParent(parent,currentUserId);
	}

	/**
	 * 
	 */
	@Override
	public Resource getResourceByCode(String msgCode) {
		if(StringUtils.isNotBlank(msgCode)){
			return resDao.getResourceByCode(msgCode);
		}
		return null;
	}

	/**
	 * 所有的菜单的信息i
	 */
	@Override
	public List<Resource> getAdminResource() {
		
		return resDao.getAdminResource();
	}

}
