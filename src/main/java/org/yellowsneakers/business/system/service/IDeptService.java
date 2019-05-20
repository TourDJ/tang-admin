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
import org.yellowsneakers.business.system.model.Dept;
import org.yellowsneakers.business.system.model.UserInfo;
import org.yellowsneakers.business.system.vo.DeptVO;
import org.yellowsneakers.generic.archetype.tree.Node;
import org.yellowsneakers.generic.query.PageQuery;

import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  部门服务类
 *
 * @author Blade
 * @since 2018-05-03
 */
public interface IDeptService extends BusinessService<Dept> {

	/**
	 * 查询部门
	 * @param dept 查询部门的条件
	 * @param query 分页工具
	 * @param userInfo 用户实体
	 * @return
	 */
	IPage<Node> queryDept(Dept dept, PageQuery query, UserInfo userInfo);

	/**
	 * 保存部门
	 * @param dept
	 * @return
	 */
	boolean saveDept(Dept dept);

	/**
	 * 编辑部门
	 * @param dept
	 * @return
	 */
	boolean updateDept(Dept dept);
	
	/**
	 * 获取所有有效部门(查询条件)
	 * @return
	 */
	List<DeptVO> getAllQueryDepts();
	
	/**
	 * 获取所有有效部门
	 * @return
	 */
	List<DeptVO> getAllDepts();

	/**
	 * 获取所有有效部门
	 * @return
	 */
	List<Dept> allValidDepts();
	
	/**
	 * 获取所有有效部门
	 * @param query
	 * @param args
	 * @return
	 */
	IPage<Dept> allValidDepts(PageQuery query, Map<String, Object> args);
	
	/**
	 * 计算部门的排序
	 * @param dept
	 * @return
	 */
	Integer sortCount(Dept dept);

	/**
	 * 校验部门名称是否存在
	 * @param d 部门
	 * @return
	 */
	Boolean checkDeptName(Dept d);
	
	/**
	 * 部门树
	 * @return
	 */
	List<Node> deptTree();

	/**
	 * 部门树，带顶级节点
	 * @return
	 */
	List<Node> deptTreeWithRoot();

	/**
	 * 封装部门为map
	 * @return
	 */
	Map<Integer, String> wrapDept();

}
