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

import org.yellowsneakers.business.system.model.Dict;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 *  Mapper 接口
 *
 * @author tang
 * @since 2018-04-30
 */
public interface DictMapper extends BaseMapper<Dict> {

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
}
