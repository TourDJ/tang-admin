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
 * 部门树节点
 * @author tang
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeptNode extends HierarchyNode {
	
	private String fullName;		//部门全称
	private String name;			//部门名称
	private Integer parentId;		//父部门id
	private String parentName;		//父部门名称
	private Integer headId;			//部门领导人id
	private String headName;		//部门领导人姓名
	private Integer sort;			//排序
	private String remark;			//备注

	public DeptNode(Integer id, Integer parentId, String label, Integer value, Integer key) {
        this.id = id;
        this.parentId = parentId;
        this.label = label;
        this.value = value;
        this.key = key;
	}

}
