/*
 * Copyright 2018-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.yellowsneakers.business.system.service;

import java.util.List;
import java.util.Map;

import org.yellowsneakers.business.BusinessService;
import org.yellowsneakers.business.system.model.User;
import org.yellowsneakers.business.system.model.UserInfo;
import org.yellowsneakers.business.system.vo.UserVO;
import org.yellowsneakers.generic.query.PageQuery;

import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务类
 *
 * @author Blade
 * @since 2018-04-30
 */
public interface IUserService extends BusinessService<User> {

	/**
	 * 查询用户
	 * @return
	 */
    List<User> selectUserList();
	
	/**
	 * 根据帐号、状态获取用户
	 * 
	 * @param account
	 * @return
	 */
	User getUserByCount(String account);

    /**
     * 条件查询用户
     * @param deptService
     * @param roleService
     * @param dictService
     * @param user
     * @param query
     * @return
     */
    IPage<User> queryUser(IDeptService deptService, IRoleService roleService, IDictService dictService, User user, PageQuery query);

	/**
	 * 保存用户
	 * @param user
	 * @return
	 */
    Boolean saveUser(User user);
    
    /**
     * 登录用户验证
     * @param account
     * @param password
     * @param roleMenuService
     * @return
     */
    UserInfo userInfo(String account, String password, IRoleMenuService roleMenuService);
    
    /**
     * 返回登录信息
     * @param menuService
     * @param userInfo
     * @return
     */
    UserInfo loginInfo(IMenuService menuService, UserInfo userInfo);
    
	/**
	 * 所有有效用户
	 * @return
	 */
    List<UserVO> warpLoginUser(List<User> loginUsers);
    
	/**
	 * 审核用户查询
	 * @param deptService
	 * @param roleService
	 * @param dictService
	 * @param map
	 * @param user
	 * @param query
	 * @return
	 */
    IPage<User> obtainUser(IDeptService deptService, IRoleService roleService,
			IDictService dictService, Map<String, Object> map, User user, PageQuery query);
    
    /**
     * 审核列表
     * @param deptService
     * @param roleService
     * @param dictService
     * @param user
     * @param query
     * @return
     */
    IPage<User> auditUser(IDeptService deptService, IRoleService roleService, IDictService dictService, User user, PageQuery query);

    /**
     * 审核操作
     * @param users
     * @param tag
     * @param userInfo
     * @return
     */
	Boolean auditOperate(List<User> users, Integer tag, UserInfo userInfo);

	/**
	 * 回收站
	 * @param deptService
	 * @param roleService
	 * @param dictService
	 * @param user
	 * @param query
	 * @return
	 */
	IPage<User> recycle(IDeptService deptService, IRoleService roleService, IDictService dictService, User user, PageQuery query);

	/**
	 * 回收站操作
	 * @param users
	 * @param tag
	 * @param userInfo
	 * @return
	 */
	boolean recycleOperate(List<User> users, Integer tag, UserInfo userInfo);

	/**
	 * 冻结列表
	 * @param deptService
	 * @param roleService
	 * @param dictService
	 * @param user
	 * @param query
	 * @return
	 */
	IPage<User> freeze(IDeptService deptService, IRoleService roleService, IDictService dictService, User user,
			PageQuery query);

	/**
	 * 冻结解冻帐号
	 * @param users
	 * @param tag
	 * @param userInfo
	 * @return
	 */
	boolean freezeOperate(List<User> users, Integer tag, UserInfo userInfo);

	/**
	 * 重置密码
	 * @param users
	 * @return
	 */
	boolean resetPassword(List<User> users);

	/**
	 * 分配角色
	 * @param users
	 * @param roleId
	 * @return
	 */
	boolean assignRole(List<User> users, String roleId);
	
	/**
	 * 封装用户信息为map
	 * @return
	 */
	Map<Integer, String> loginUsers();
}
