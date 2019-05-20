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

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.yellowsneakers.generic.utils.CommonUtils;

/**
 * 路由节点工具类
 * @author tang
 *
 */
public class RouteNodeMerger {

	
    /**
     * 将节点数组归并为一个森林（多棵树）（填充节点的children域）
     * 时间复杂度为O(n^2)
     *
     * @param items 节点域
     * @return 多棵树的根节点集合
     */
    public static List<IRouteNode> merge(List<IRouteNode> items) {
    	RouteNodeManager routeNodeManager = new RouteNodeManager(items);
        for (IRouteNode routeNode : items) {
            if (routeNode.getParentId() != 0) {
            	IRouteNode t = routeNodeManager.getTreeNodeAT(routeNode.getParentId());
                t.getRoutes().add(routeNode);
            }
        }
        return routeNodeManager.getRoot();
    }

	/**
	 * 在树节点中条件查询
	 * 
	 * @param trees 所有树节点
	 * @param params 条件查询参数
	 * @return
	 */
	public static boolean nodeSearch(List<IRouteNode> trees, Map<String, Object> params) {
		boolean flag = false;
		if (trees != null && params != null) {
			for (Iterator<IRouteNode> iterator = trees.iterator(); iterator.hasNext();) {
				IRouteNode node = iterator.next();
				List<IRouteNode> children = node.getRoutes();
				if ((children == null || children.size() <= 0) && !involve(node, params)) {
					iterator.remove();
				} else {

					if (children != null) {
						flag = nodeSearch(children, params);
					}

					if (involve(node, params)) {
						flag = true;
					}

					if (!flag && (children == null || children.size() == 0)) {
						iterator.remove();
					}
				}
			}
		}
		return flag;
	}

	/**
	 * 节点条件过滤逻辑
	 * @param node 树节点
	 * @param params 查询参数
	 * @return
	 */
	private static boolean involve(IRouteNode node, Map<String, Object> params) {
		boolean flag = true;
		if (node != null && params != null) {
			String key = null;
			Object value = null;
			Object o = null;
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				try {
					key = entry.getKey();
					value = entry.getValue();
					o = CommonUtils.getFieldValue(node, key);
					if (o != null && value != null) {
						if (o instanceof String && value instanceof String) {
							if (!((String) o).contains((String) value)) {
								flag = false;
								break;
							}
						} else if (o instanceof Integer) {
							if (value.getClass().isArray()) {
								List<Integer> list = Arrays.asList((Integer[]) value);
								if (!list.contains(o)) {
									flag = false;
									break;
								}
							} else if (value instanceof Integer) {
								if (((Integer) o).intValue() != ((Integer) value).intValue()) {
									flag = false;
									break;
								}
							}
						} else {
							flag = false;
						}
					} else {
						flag = false;
					}
				} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	/**
	 * 去除按钮节点
	 * @param trees 节点集合
	 */
	public static void removeButtons(List<IRouteNode> trees) {
		List<IRouteNode> routes = null;
		Integer kind = null;
		for (Iterator<IRouteNode> iterator = trees.iterator(); iterator.hasNext();) {
			IRouteNode node = iterator.next();
			kind = node.getKind();
			if(kind.intValue() == 2)
				iterator.remove();
			else {
				routes = node.getRoutes();
				if(routes != null && routes.size() > 0) {
					removeButtons(routes);
				}
			}
		}
	}
    
}
