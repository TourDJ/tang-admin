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
 * 菜单实体类
 * @author tang
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
@TableName("blade_menu")
public class Menu extends SystemModel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 菜单类别
	 */
	private Integer kind;
	
	/**
	 * 菜单名称
	 */
	private String name;
	
	/**
	 * 菜单编号
	 */
	private String code;
	
	/**
	 * 父菜单
	 */
	@TableField("parent_id")
	private Integer parentId;
	
	/**
	 * 按钮别名
	 */
	private String alias;
	
	/**
	 * 是否弹窗
	 */
	private Integer tab;
	
	/**
	 * 路由地址
	 */
	private String url;
	
	/**
	 * 组件地址
	 */
	@TableField("operate_button")
	private Integer operateButton;
	
	/**
	 * 排序
	 */
	private Integer sort;
	
	/**
	 * 层级
	 */
	private Integer classify;
	
	/**
	 * 图标样式
	 */
	private String icon;
	
	/**
	 * 图标地址
	 */
	private String logo;
	
	/**
	 * 备注
	 */
	private String remark; 
	
	/**
	 * 菜单父编号
	 */
	@TableField("parent_code")
	private String parentCode; 
	
	@TableField("parent_name")
	private String parentName;

}
