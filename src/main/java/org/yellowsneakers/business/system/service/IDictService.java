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
import org.yellowsneakers.business.system.model.Dict;
import org.yellowsneakers.business.system.model.UserInfo;
import org.yellowsneakers.business.system.vo.DictVO;
import org.yellowsneakers.generic.archetype.tree.Node;
import org.yellowsneakers.generic.query.PageQuery;

import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  字典服务类
 *
 * @author tang
 * @since 2018-04-30
 */
public interface IDictService extends BusinessService<Dict> {

	/**
	 * 根据字典的code和key查询字典
	 * @param map code,key属性
	 * @return
	 */
    List<String> selectByCodeAndKey(Map<String, Object> map);
    
    /**
     * 查找字典的子项
     * @param dict
     * @return
     */
    List<Dict> selectSubType(Dict dict);
    
	/**
	 * 根据code查询字典数据，包装成map
	 * @param code
	 * @return
	 */
	Map<Integer, String> getDictMap(String code);
	
	/**
	 * 根据条件查询字典数据
	 * @param dict 字典实体类
	 * @param query
	 * @param userInfo
	 * @return
	 */
	IPage<Node> queryDict(Dict dict, PageQuery query, UserInfo userInfo);

	/**
	 * 保存字典
	 * @param dict
	 * @return
	 */
	boolean saveDict(Dict dict);

	/**
	 * 更新字典
	 * @param dict
	 * @return
	 */
	boolean updateDict(Dict dict);

	/**
	 * 计算字典的排序
	 * @param dict
	 * @return
	 */
	int getSortCount(Dict dict);
	
	/**
	 * 计算字典的序号
	 * @param dict
	 * @return
	 */
	int getKeyCount(Dict dict);

	/**
	 * 字典树
	 * @return
	 */
	List<Node> dictTree();

	/**
	 * 字典树，带顶级节点
	 * @return
	 */
	List<Node> dictTreeWithRoot();
	
	/**
	 * 获取所有有效字典
	 * @return
	 */
	List<Dict> allValidDicts();
	
	/**
	 * 获取所有有效字典
	 * @param args
	 * @return
	 */
	List<Dict> allValidDicts(Map<String, Object> args);
	
	/**
	 * 获取所有有效字典
	 * @param query
	 * @param args
	 * @return
	 */
	IPage<Dict> allValidDicts(PageQuery query, Map<String, Object> args);

	/**
	 * 根据指定类型获取所有的子类型
	 * 
	 * @param dict
	 * @return
	 */
	List<DictVO> querySubType(Dict dict);

	/**
	 * 查询字典
	 * @param dict
	 * @param query
	 * @return
	 */
	List<DictVO> getDicts(Dict dict);

	/**
	 * 查询字典，带查询条件
	 * @param dict
	 * @return
	 */
	List<DictVO> getQueryDicts(Dict dict);

	/**
	 * 查询字典，带过滤条件
	 * @param dict
	 * @return
	 */
	List<DictVO> getFilterDicts(Dict dict);

	/**
	 * 查询字典，带查询和过滤条件
	 * @param dict
	 * @return
	 */
	List<DictVO> getQueryFilterDicts(Dict dict);

	/**
	 * 根据字典节点查询 
	 * @param query
	 * @param id
	 * @return
	 */
	IPage<Node> dictNodeTree(PageQuery query, Integer id);
		
}
