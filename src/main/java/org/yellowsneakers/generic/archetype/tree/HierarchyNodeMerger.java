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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.yellowsneakers.generic.utils.CommonUtils;

/**
 * 层级节点工具类
 * 
 * @author tang
 */
public class HierarchyNodeMerger extends ForestNodeMerger {

    /**
     * 将节点数组组合成一个树
     * @param items 节点域
     * @param top 是否需要顶级节点
     * @return 根节点集合
     */
    public static List<Node> merge(List<Node> items, boolean top) {
    	List<Node> nodeList = merge(items);
    	if(!top)
    		return nodeList;
    	
    	return addTopNode(nodeList);
    }

    /**
     * 添加顶级节点
     * @param nodeList 节点域
     * @return 根节点集合
     */
	private static List<Node> addTopNode(List<Node> nodeList) {
		List<Node> nodes = new ArrayList<>();
    	Node root = new TopNode(0, null, "顶级", 0, 0);
    	for (Node node : nodeList) {
			if(node.getParentId() == 0) {
				root.getChildren().add(node);
			}
		}
    	nodes.add(root);
		return nodes;
	}
	
	/**
	 * 当树集合中的树节点的children为空集合时，设为空值
	 * @param nodes 树节点集合
	 */
	public static void checkNullChildren(List<Node> nodes) {
		for (Node node : nodes) {
			checkNullChildren(node);
		}
	}
	
	/**
	 * 当树节点的children为空集合时，设为空值
	 * @param node 树节点
	 */
	public static void checkNullChildren(Node node) {
		HierarchyNode hNode = (HierarchyNode) node;
		if(hNode.getChildren().size() == 0) {
			hNode.setChildren(null);
		}
	}

	/**
	 * 在树节点中条件查询
	 * 
	 * @param trees 所有树节点
	 * @param params 条件查询参数
	 * @return
	 */
	public static boolean nodeSearch(List<Node> trees, Map<String, Object> params) {
		boolean flag = false;
		if (trees != null && params != null) {
			for (Iterator<Node> iterator = trees.iterator(); iterator.hasNext();) {
				Node node = (Node) iterator.next();
				List<Node> children = (List<Node>) node.getChildren();
				if ((children == null || children.size() <= 0) && !involve(node, params)) {
					iterator.remove();
				} else {

					if (children != null) {
						flag = nodeSearch(children, params);
						if (children.size() == 0)
							HierarchyNodeMerger.checkNullChildren(node);
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
	private static boolean involve(Node node, Map<String, Object> params) {
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
	 * 查找指定节点及其所有子节点
	 * @param nodes 所有节点
	 * @param id 要查找节点的 id
	 * @return
	 */
	public static List<Node> selectNodes(List<Node> nodes, Integer id) {
		List<Node> outNodes = null;
		List<Node> children = null;
		for (Node node : nodes) {
			if(node.getId().intValue() == id.intValue()) {
				outNodes = new ArrayList<>();
				outNodes.add(node);
				break;
			} else {
				children = node.getChildren();
				if(children != null && children.size() > 0) {
					outNodes = selectNodes(children, id);
					if(outNodes != null)
						break;
				}
			}
		}
		return outNodes;
	}

}
