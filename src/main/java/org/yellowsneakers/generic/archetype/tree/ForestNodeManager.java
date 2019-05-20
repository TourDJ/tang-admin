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
 * Forest node manager.
 *
 * @author tang
 */
public class ForestNodeManager {

    private List<Node> list;// 森林的所有节点

    public ForestNodeManager(Node[] items) {
        list = new ArrayList<>();
        for (Node forestNode : items) {
            list.add(forestNode);
        }
    }

    public ForestNodeManager(List<Node> items) {
        list = items;
    }

    /**
     * 根据节点ID获取一个节点
     *
     * @param id 节点ID
     * @return 对应的节点对象
     */
    public Node getTreeNodeAT(int id) {
        for (Node forestNode : list) {
            if (forestNode.getId() == id)
                return forestNode;
        }
        return null;
    }

    /**
     * 获取树的根节点【一个森林对应多颗树】
     *
     * @return 树的根节点集合
     */
    public List<Node> getRoot() {
        List<Node> roots = new ArrayList<>();
        for (Node forestNode : list) {
            if (forestNode.getParentId() != null && forestNode.getParentId() == 0)
                roots.add(forestNode);
        }
        return roots;
    }

}
