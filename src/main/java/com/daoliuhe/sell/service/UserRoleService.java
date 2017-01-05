package com.daoliuhe.sell.service;


import com.daoliuhe.sell.model.UserRole;

public interface UserRoleService {
	
	/**
	 * 获取角色的分页数据
	 * @param relation
	 * @return
	 */
	Object getRolePageData(UserRole relation);
	

	/**
	 * 根据对象属性保存关系表数据
	 * @param relation
	 * @return
	 */
	Object saveRelation(UserRole relation);

	/**
	 * 根据用户的id和角色的多个id删除关系表中的数据
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	Object deleteForUser(String userId, String roleIds);

	/**
	 * 根据多个角色id和单个用户id保存到关系表中
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	Object saveBatchRole(String userId, String roleIds);
	
}
