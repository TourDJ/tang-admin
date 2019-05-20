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
 * 菜单树节点
 * 
 * @author tang
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MenuNode extends HierarchyNode {

	private String name; 		// 菜单名称
	private String code; 		// 菜单编号
	private String parentCode; 	// 菜单父编号
	private String url; 		// 路由地址
	private String icon; 		// 图标样式
	private Integer sort; 		// 排序
	private Integer classify; 	// 层级
	private Integer operateButton; 		// 组件地址
	private String alias;		// 别名
	private String remark;		// 备注
	private String logo;		// 图标地址
	private Integer kind;		// 菜单类别
	private Integer tab;		// 是否弹窗
	private Integer status;		// 状态
	private String parentName; 	//父菜单名称

	public MenuNode(Integer id, Integer parentId, String label, Integer value, Integer key) {
        this.id = id;
        this.parentId = parentId;
        this.label = label;
        this.value = value;
        this.key = key;
	}

}
