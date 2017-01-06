package com.daoliuhe.sell.util;

/**
 * 系统常量
 * 
 * @author 21829
 * @date 2016年7月13日
 */
public class Constants {
	/**
	 * 用户信息
	 */
	public final static String USERINFO = "userInfo";

	/**
	 * 客户端ip地址 
	 */
	public final static String USER_IP = "userIp";
	
	/**
	 * 用户强制退出标识 
	 */
	public final static String SESSION_FORCE_LOGOUT_KEY = "session.force.logout";
	
	/**
	 * 有效的，启用的
	 */
	public final static String ENABLE = "1";

	/**
	 * 无效的，禁用的
	 */
	public final static String DISABLE = "0";

	/**
	 * 文件下载状态 1 开始下载 (1:开始下载，2：下载完成， -1：下载出错)
	 */
	public final static String LOAD_FILE_STATUS_START = "1";
	
	/**
	 * 文件下载状态 2 下载完成 (1:开始下载，2：下载完成， -1：下载出错)
	 */
	public final static String LOAD_FILE_STATUS_FINISH = "2";
	
	/**
	 * 文件下载状态 -1 下载出错 (1:开始下载，2：下载完成， -1：下载出错)
	 */
	public final static String LOAD_FILE_STATUS_ERROR = "-1";
	
}
