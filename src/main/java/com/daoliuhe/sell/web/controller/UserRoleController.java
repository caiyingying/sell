package com.daoliuhe.sell.web.controller;

import com.daoliuhe.sell.model.UserRole;
import com.daoliuhe.sell.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/userRole")
public class UserRoleController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserRoleController.class);

	@Resource
	UserRoleService userRoleService;
	
	/**
	 * 根据用户查找角色
	 * @param entity
	 * @return
	 */
	@RequestMapping("/roleData")
	@ResponseBody
	public Object roleData(UserRole entity) {
		logger.info("roleData,entity:{}",entity);
		return userRoleService.getRolePageData(entity);
	}
	
	/**
	 * 保存用户角色关系
	 * @param entity
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Object saveRelation(UserRole entity) {
		logger.info("saveRelation,entity:{}",entity);
		return userRoleService.saveRelation(entity);
	}
	
	/**
	 * 删除用户角色的关系
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	@RequestMapping("/deleteForUser")
	@ResponseBody
	public Object deleteForUser(String userId, String roleIds) {
		logger.info("deleteForUser,userId:{},roleIds:{}",userId,roleIds);
		return userRoleService.deleteForUser(userId, roleIds);
	}

	/**
	 * 根据用户批量添加角色
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	@RequestMapping("/saveBatchRole")
	@ResponseBody
	public Object saveBatchRole(String userId, String roleIds) {
		logger.info("saveBatchRole,userId:{},roleIds:{}",userId,roleIds);
		return userRoleService.saveBatchRole(userId, roleIds);
	}
}
