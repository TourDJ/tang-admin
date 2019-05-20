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

import org.yellowsneakers.business.system.model.User;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 *  Mapper 接口
 *
 * @author tang
 * @since 2018-04-30
 */
public interface UserMapper extends BaseMapper<User> {
    
	/**
	 * 查询用户
	 * @return
	 */
    List<User> selectUserList();
    
    /**
     * 根据帐号密码查询用户
     * @param map
     * @return
     */
    User getUser(Map<String, String> map);

    /**
     * 根据角色 id数组查询角色
     * @param ids
     * @return
     */
    List<String> getRoleName(Integer[] ids);
}
