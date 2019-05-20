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
import org.yellowsneakers.business.system.model.Role;
import org.yellowsneakers.business.system.vo.RoleVO;
import org.yellowsneakers.generic.archetype.tree.Node;
import org.yellowsneakers.generic.query.PageQuery;

import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 角色服务接口
 * @author service1
 *
 */
public interface IRoleService extends BusinessService<Role> {

	/**
	 * 查询所有父级节点
	 * @return
	 */
	List<Role> selectParentRoles();

	/**
	 * 获取所有父级节点
	 * @return
	 */
	List<RoleVO> getParentRoles();
	
	/**
	 * 条件查询角色
	 * @param menuService
	 * @param roleMenuService
	 * @param role
	 * @param query
	 * @param userId
	 * @return
	 */
	IPage<Node> queryRole(IMenuService menuService, IRoleMenuService roleMenuService, Role role, PageQuery query, Integer userId);

	/**
	 * 新增角色
	 * @param role
	 * @return
	 */
	Boolean saveRole(Role role);

	/**
	 * 编辑角色
	 * @param role
	 * @return
	 */
	Boolean updateRole(Role role);
	
	/**
	 * 获取角色序号
	 * @param role
	 * @return
	 */
	Integer sortCount(Role role);
	
	/**
	 * 根据名称检验角色
	 * @param name
	 * @param id
	 * @return
	 */
	Boolean checkRoleByName(String name, Integer id);

	/**
	 * 查询所有的角色
	 * @return
	 */
	List<Role> allValidRoles();

	/**
	 * 角色树
	 * @return
	 */
	List<Node> roleTree();

	/**
	 * 角色树，带顶级节点
	 * @return
	 */
	List<Node> roleTreeWithRoot();

	/**
	 * 查询角色封装成map
	 * @return
	 */
	List<RoleVO> getRoles();
	
	/**
	 * 封装角色为map
	 * @return
	 */
	Map<Integer, String> wrapRole();

}
