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
package org.yellowsneakers.business.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yellowsneakers.business.system.mapper.DeptMapper;
import org.yellowsneakers.business.system.model.Dept;
import org.yellowsneakers.business.system.model.UserInfo;
import org.yellowsneakers.business.system.node.DeptNode;
import org.yellowsneakers.business.system.service.IDeptService;
import org.yellowsneakers.business.system.vo.DeptVO;
import org.yellowsneakers.generic.archetype.tree.HierarchyNodeMerger;
import org.yellowsneakers.generic.archetype.tree.Node;
import org.yellowsneakers.generic.query.PageQuery;
import org.yellowsneakers.generic.query.WrapperProcess;
import org.yellowsneakers.generic.utils.CommonConstants;
import org.yellowsneakers.generic.utils.CommonUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 部门服务实现类
 *
 * @author Blade
 * @since 2018-05-03
 */
@Service
public class DeptServiceImpl extends SystemServiceImpl<DeptMapper, Dept> implements IDeptService, CommonConstants {

	/**
	 * 查询部门
	 * @param dept 查询部门的条件
	 * @param query 分页工具
	 * @param userInfo 用户实体
	 * @return
	 */
	@Override
	public IPage<Node> queryDept(Dept dept, PageQuery query, UserInfo userInfo) {
//		IPage<Node> pages = Condition.getPage(query);
//		if (dept != null) {
//			String name = dept.getName();
//			String fullName = dept.getFullName();
//			if (!CommonUtils.notEmpty(name) && !CommonUtils.notEmpty(fullName)) {
//				filterDept(pages);
//			} else {
//				filterDeptWithCondition(pages, query, dept, name, fullName);
//			}
//		}
//		return pages;
		return null;
	}

    /**
     * 无条件查询部门
     * @param pages
     */
    private void filterDept(IPage<Node> pages) {
		List<Dept> depts = this.allValidDepts();
    	List<Node> deptTrees = produceDeptTreeFull(depts);
    	HierarchyNodeMerger.checkNullChildren(deptTrees);
		pages.setRecords(deptTrees);	
    }

    /**
     * 有条件查询部门
     * @param pages
     * @param query
     * @param dept
     * @param name
     * @param fullName
     */
	private void filterDeptWithCondition(IPage<Node> pages, PageQuery query, Dept dept, String name, String fullName) {
		List<Dept> depts = this.allValidDepts();
		List<Node> deptTrees = produceDeptTreeFull(depts);
		Map<String, Object> params = new HashMap<>();
		if (CommonUtils.notEmpty(name)) {
			params.put("name", name);
		}
		if (CommonUtils.notEmpty(fullName)) {
			params.put("fullName", fullName);
		}
		HierarchyNodeMerger.nodeSearch(deptTrees, params);
		dept.assembling(query);
		dept.setPages(pages, deptTrees, deptTrees.size());
	}

	/**
	 * 保存部门
	 * @param dept
	 * @return
	 */
	@Override
	@Transactional
	public boolean saveDept(Dept dept) {
		defaultRoot(dept);
		boolean flag = this.save(dept);
		return flag;
	}

	/**
	 * 编辑部门
	 * @param dept
	 * @return
	 */
	@Override
	@Transactional
	public boolean updateDept(Dept dept) {
		defaultRoot(dept);
		boolean flag = this.updateById(dept);
		return flag;
	}

	/**
	 * 设置默认根节点
	 * @param dept
	 */
	private void defaultRoot(Dept dept) {
		if (dept.getParentId() == null) {
			dept.setParentId(DEFAULT_DEPT_ROOT_CODE);
			dept.setParentName(DEFAULT_DEPT_ROOT_NAME);	
		}
	}

	/**
	 * 获取所有部门
	 * @return
	 */
	@Override
	public List<DeptVO> getAllQueryDepts() {
		List<DeptVO> allDepts = new ArrayList<>();
		DeptVO deptVO = new DeptVO();
		deptVO.setId(-1);
		deptVO.setName("全部");
		allDepts.add(deptVO);
		List<DeptVO> deptVOs = getAllDepts();
		allDepts.addAll(deptVOs);
		return allDepts;
	}
	
	/**
	 * 获取所有有效部门
	 * @return
	 */
	@Override
	public List<DeptVO> getAllDepts() {
		List<DeptVO> deptVOs = new ArrayList<>();
		DeptVO deptVO = null;
		List<Dept> deptList = this.allValidDepts();
		for (Dept dept : deptList) {
			deptVO = new DeptVO();
			deptVO.setId(dept.getId());
			deptVO.setName(dept.getFullName());
			deptVOs.add(deptVO);
		}
		return deptVOs;
	}

	/**
	 * 获取所有有效部门
	 * @return
	 */
	@Override
	public List<Dept> allValidDepts() {
		return allValidDepts(new PageQuery(), null).getRecords();
	}
	
	/**
	 * 获取所有有效部门
	 * @param query
	 * @param args
	 * @return
	 */
	@Override
	public IPage<Dept> allValidDepts(PageQuery query, Map<String, Object> args) {
//		if(query.getSize() == null || query.getSize() <= 0)
//			query.setSize(1000);
//		
//		IPage<Dept> pages = Condition.getPage(query);
//    	QueryWrapper<Dept> qw = Condition.getQueryWrapper(args, Dept.class);		
//    	WrapperProcess.ensureStatus(qw);
//		WrapperProcess.sortRecords(qw, false, "parent_id", "sort", "full_name");
//		this.page(pages, qw);
//		return pages;
		return null;
	}
	
	/**
	 * 计算部门的排序
	 * @param dept
	 * @return
	 */
	@Override
	public Integer sortCount(Dept dept) {
		int count = 0;
		if (dept != null) {
			Integer id = dept.getId();
			QueryWrapper<Dept> qw = new QueryWrapper<>();
			qw.setEntity(new Dept());
			qw.eq("parent_id", id);
			WrapperProcess.ensureStatus(qw);
			count = this.count(qw);
		}
		return count;
	}


	/**
	 * 校验部门名称是否存在
	 * @param d 部门
	 * @return
	 */
	@Override
    public Boolean checkDeptName(Dept d) {
        boolean flag = false;
        if (d != null) {
        	Integer id = d.getId();
            Map<String, Object> map = new HashMap<>();
            if (d.getName() != null) {
                map.put("name", d.getName());
            }
            if (d.getFullName() != null) {
                map.put("full_name", d.getFullName());
            }
            Dept dept = this.getDept(map);
            if (id == null) {
                // 新增
                flag = dept != null;
            } else {
                // 编辑
                flag = (dept != null && dept.getId() != id);
            }
        }
        return flag;
    }

	/**
	 * 查询部门
	 * @param map
	 * @return
	 */
	private Dept getDept(Map<String, Object> map) {
//		QueryWrapper<Dept> qw = Condition.getQueryWrapper(map, Dept.class);
//		WrapperProcess.ensureStatus(qw);
//		Dept dept = this.getOne(qw);
//		return dept;
		return null;
	}
	
	/**
	 * 部门树
	 * @return
	 */
	@Override
	public List<Node> deptTree() {
		return getDeptTree(false);
	}

	/**
	 * 部门树，带顶级节点
	 * @return
	 */
	@Override
	public List<Node> deptTreeWithRoot() {
		return getDeptTree(true);
	}

	/**
	 * 生成部门树节点
	 * @param top 是否有顶级节点
	 * @return
	 */
	private List<Node> getDeptTree(boolean top) {
		List<Dept> depts = this.allValidDepts();
		List<Node> deptTrees = produceDeptTree(depts, top);
		HierarchyNodeMerger.checkNullChildren(deptTrees);
		return deptTrees;
	}

	/**
	 * 部门树
	 * @param depts 部门列表
	 * @param top  是否包含顶级节点
	 * @return
	 */
	private List<Node> produceDeptTree(List<Dept> depts, boolean top) {
		List<Node> list = new ArrayList<>();
		for (Dept dept : depts) {
			list.add(addDeptNode(dept, false));
		}
		return HierarchyNodeMerger.merge(list, top);
	}
	
	/**
	 * 部门树，包括全部属性
	 * @param depts 部门列表
	 * @return
	 */
	private List<Node> produceDeptTreeFull(List<Dept> depts) {
		List<Node> list = new ArrayList<>();
		for (Dept dept : depts) {
			list.add(addDeptNode(dept, true));
		}
		return HierarchyNodeMerger.merge(list);
	}

	/**
	 * 创建一个部门节点对象
	 * @param dept
	 * @param full 是否包含全部属性
	 * @return
	 */
	private DeptNode addDeptNode(Dept dept, boolean full) {
		DeptNode deptNode = new DeptNode(dept.getId(), dept.getParentId(), dept.getFullName(), dept.getId(), dept.getId());
		if(full) {
			deptNode.setFullName(dept.getFullName());
			deptNode.setName(dept.getName());
			deptNode.setParentId(dept.getParentId());
			deptNode.setParentName(dept.getParentName());
			deptNode.setHeadId(dept.getHead());
			deptNode.setHeadName(dept.getHeadname());
			deptNode.setSort(dept.getSort());
			deptNode.setRemark(dept.getRemark());
		}
		return deptNode;
	}

	/**
	 * 封装部门为map
	 * @return
	 */
	@Override
	public Map<Integer, String> wrapDept() {
		List<Dept> depts = allValidDepts();
		Map<Integer, String> deptMap = new HashMap<>();
		if (depts != null) {
			for (Dept dept : depts) {
				if (dept != null) {
					deptMap.put(dept.getId(), dept.getName());
				}
			}
		}
		return deptMap;
	}

}
