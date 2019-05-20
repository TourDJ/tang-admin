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
package org.yellowsneakers.generic.archetype.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 路由节点管理类
 * @author tang
 *
 */
public class RouteNodeManager {
	
	private List<IRouteNode> list;// 森林的所有节点

    public RouteNodeManager(IRouteNode[] items) {
        list = new ArrayList<>();
        for (IRouteNode route : items) {
            list.add(route);
        }
    }

    public RouteNodeManager(List<IRouteNode> items) {
        list = items;
    }

    /**
     * 根据节点ID获取一个节点
     *
     * @param id 节点ID
     * @return 对应的节点对象
     */
    public IRouteNode getTreeNodeAT(int id) {
        for (IRouteNode route : list) {
            if (route.getId() == id)
                return route;
        }
        return null;
    }

    /**
     * 获取树的根节点【一个森林对应多颗树】
     *
     * @return 树的根节点集合
     */
    public List<IRouteNode> getRoot() {
        List<IRouteNode> roots = new ArrayList<>();
        for (IRouteNode route : list) {
            if (route.getParentId() == 0)
                roots.add(route);
        }
        return roots;
    }
}
