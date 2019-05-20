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
import java.util.Set;

import org.yellowsneakers.business.BusinessService;
import org.yellowsneakers.business.system.model.Menu;
import org.yellowsneakers.business.system.model.UserInfo;
import org.yellowsneakers.business.system.vo.MenuVO;
import org.yellowsneakers.generic.archetype.tree.IRouteNode;
import org.yellowsneakers.generic.archetype.tree.Node;
import org.yellowsneakers.generic.query.PageQuery;

import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 菜单服务接口
 * @author tang
 *
 */
public interface IMenuService extends BusinessService<Menu> {

	/**
	 * 根据角色查询菜单
	 * @param map
	 * @return
	 */
	List<Menu> selectButtons(Map<String, Object> map);

	/**
	 * 条件查询菜单
	 * @param menu 菜单实体类
	 * @param query 查询对象
	 * @param userInfo 用户
	 * @return
	 */
    IPage<Node> queryMenu(Menu menu, PageQuery query, UserInfo userInfo);

	/**
	 * 保存菜单
	 * @param menu
	 * @return
	 */
	Boolean saveMenu(Menu menu);

	/**
	 * 更新菜单
	 * @param menu
	 * @return
	 */
	Boolean updateMenu(Menu menu);
	
    /**
     * 获取所有有效菜单按钮
     * @return
     */
	List<Menu> allValidMenus();
	
	/**
	 * 获取所有有效菜单或按钮
	 * @param kind
	 * @return
	 */
	List<Menu> allValidMenus(int kind);

	/**
	 * 获取所有有效菜单，根据指定条件
	 * @param query
	 * @param args
	 * @return
	 */
    IPage<Menu> allValidMenus(PageQuery query, Map<String, Object> args);
	
    /**
     * 获取菜单的路由
     * @param name
     * @return
     */
	String getUrl(String name);
	
	/**
	 * 将所有有效菜单封装成map
	 * @return
	 */
	Map<String, String> allValidMapMenus();
	
	/**
	 * 检验菜单编码是否重复
	 * @param code
	 * @param id
	 * @return
	 */
	Boolean checkMenuByCode(String code, Integer id);
	
	/**
	 * 检验菜单名称是否重复
	 * @param name
	 * @param id
	 * @return
	 */
	Boolean checkMenuByName(String name, Integer id);
	
	/**
	 * 获取菜单回收站列表
	 * @param menu
	 * @param query
	 * @return
	 */
	IPage<Menu> getRecycles(Menu menu, PageQuery query);

	/**
	 * 回收菜单处理
	 * @param menus
	 * @param tag
	 * @return
	 */
	Boolean recycle(List<Menu> menus, Integer tag, UserInfo userInfo);
	
	/**
	 * 彻底删除菜单
	 * @param menus
	 * @return
	 */
	Boolean completeDelete(List<Menu> menus);
	
	/**
	 * 还原菜单
	 * @param menus
	 * @return
	 */
	Boolean restore(List<Menu> menus, UserInfo userInfo);
	
	/**
	 * 计算新增菜单的排序
	 * @param menu
	 * @return
	 */
	Integer sortCount(Menu menu);

	/**
	 * 获取模块的菜单
	 * @param menu 菜单
	 * @param query 查询对象
	 * @param bladeUser 用户
	 * @return
	 */
	List<Menu> getButtons(Menu menu, String role);
	
	/**
	 * 给角色分配菜单权限
	 * @param roleMenuService
	 * @param records
	 */
	void addMenus(IRoleMenuService roleMenuService, List<Node> records);

	/**
	 * 包装路由菜单
	 * @param menuIds 
	 * @return
	 */
	List<IRouteNode> wrapRouteMenuTree(Set<Integer> menuIds);

	/**
	 * 删除菜单
	 * @param menu
	 * @return
	 */
	Boolean deleteMenu(List<Integer> ids);

	/**
	 * 菜单树
	 * @return
	 */
	List<Node> menuTree();

	/**
	 * 菜单树，带顶级节点
	 * @return
	 */
	List<Node> menuTreeWithRoot();

	/**
	 * 获取所有有权限的菜单
	 * @param bladeUser
	 * @return
	 */
	List<MenuVO> allButtons(String role);
}
