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

import lombok.Data;

/**
 * 层级基础节点
 *
 * @author tang
 */
@Data
public class HierarchyNode implements Node {

    protected Integer id;//主键ID
    protected Integer parentId;//父节点ID
    protected Integer key;//序号
    protected Integer value;//值
    protected String label;//对应中文
    protected List<Node> children = new ArrayList<>();//子孙节点

}
