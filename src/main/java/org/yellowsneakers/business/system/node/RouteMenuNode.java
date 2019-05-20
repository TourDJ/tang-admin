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

import java.util.ArrayList;
import java.util.List;

import org.yellowsneakers.generic.archetype.tree.IRouteNode;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 路由菜单树节点
 * 
 * @author tang
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RouteMenuNode implements IRouteNode {
	
	private Integer id;//主键ID
	private Integer parentId;//父节点ID
	private String path;
	private String name;
	private String icon;
	private Integer kind;
	private List<IRouteNode> routes = new ArrayList<>(); //子孙节点;

	public RouteMenuNode(Integer id, Integer parentId) {
        this.id = id;
        this.parentId = parentId;
	}

}
