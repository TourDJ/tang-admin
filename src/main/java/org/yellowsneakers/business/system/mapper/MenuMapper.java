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
package org.yellowsneakers.business.system.mapper;

import java.util.List;
import java.util.Map;

import org.yellowsneakers.business.system.model.Menu;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 
 * @author tang
 *
 */
public interface MenuMapper extends BaseMapper<Menu> {

	/**
	 * 根据角色查询菜单
	 * @param map
	 * @return
	 */
	List<Menu> selectButtons(Map<String, Object> map);
	
	/**
	 * 根据菜单id批量查询菜单
	 * @param list
	 * @return
	 */
	List<Menu> selectMenusByIds(List<Integer> list);
}
