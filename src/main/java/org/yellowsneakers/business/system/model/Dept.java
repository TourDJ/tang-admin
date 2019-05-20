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
package org.yellowsneakers.business.system.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  部门实体类
 *
 * @author tang
 * @since 2018-05-03
 */
@Data
@EqualsAndHashCode(callSuper=true)
@TableName("blade_dept")
public class Dept extends SystemModel {

    private static final long serialVersionUID = 1L;

    /**
     * 父主键
     */
    @TableField("parent_id")
    private Integer parentId;
    
    /**
     * 部门名称
     */
    private String name;
    
    /**
     * 部门全称
     */
    @TableField("full_name")
    private String fullName;
    
    /**
     * 排序
     */
    private Integer sort;
    
    /**
     * 备注
     */
    private String remark;

    /**
     * 父部门名称
     */
	@TableField("parent_name")
    private String parentName;
	
	/**
	 * 部门领导人id
	 */
	private Integer head;
	
	/**
	 * 部门领导人姓名
	 */
	private String headname;

}
