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
package org.yellowsneakers.business.system.node;

import org.yellowsneakers.generic.archetype.tree.HierarchyNode;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色节点
 * @author tang
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleNode extends HierarchyNode {

	private String name;		//角色名称
	private String parentName;	//角色父节点名称
	private String menus; 		//菜单
	private String remark;
	private Integer sort;
	
	public RoleNode(Integer id, Integer parentId, String label, Integer value, Integer key) {
        this.id = id;
        this.parentId = parentId;
        this.label = label;
        this.value = value;
        this.key = key;
	}

}
