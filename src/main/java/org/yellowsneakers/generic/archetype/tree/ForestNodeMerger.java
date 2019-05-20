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

import java.util.List;

/**
 * 森林节点归并类
 *
 * @author tang
 */
public class ForestNodeMerger {

    /**
     * 将节点数组归并为一个森林（多棵树）（填充节点的children域）
     * 时间复杂度为O(n^2)
     *
     * @param items 节点域
     * @return 多棵树的根节点集合
     */
    public static List<Node> merge(List<Node> items) {
        ForestNodeManager forestNodeManager = new ForestNodeManager(items);
        for (Node forestNode : items) {
            if (forestNode.getParentId() != null && forestNode.getParentId() != 0) {
                Node t = forestNodeManager.getTreeNodeAT(forestNode.getParentId());
                if(t != null)
                	t.getChildren().add(forestNode);
            }
        }
        return forestNodeManager.getRoot();
    }

}
