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
package org.yellowsneakers.business.system.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author tang
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
@TableName("blade_role_menu")
public class RoleMenu extends SystemModel {

	private static final long serialVersionUID = 1L;
	
	@TableField("menu_id")
	private Integer menuId;
	
	@TableField("role_id")
	private Integer roleId;
	
	@TableField(exist=false)
	private String menuIds;
}
