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

import java.io.Serializable;
import java.util.List;

import org.yellowsneakers.generic.query.PageQuery;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.metadata.IPage;

import lombok.Data;

/**
 * 
 * @author tang
 *
 */
@Data
public class SystemModel implements Serializable {
	
	private static final long serialVersionUID = 3699815864487040479L;
    
	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	
	@TableField(exist=false)
	private Integer key;

	/**
	 * 状态[1:正常]
	 */
	private Integer status;

	/**
	 * total records
	 */
	@TableField(exist=false)
	private Integer totals; 
	
	/**
	 * every records
	 */
	@TableField(exist=false)
	private Integer limit; 	 
	
	/**
	 * total pages
	 */
	@TableField(exist=false)
	private Integer pages; 	
	
	/**
	 * current page
	 */
	@TableField(exist=false)
	private Integer current;
	
	/**
	 * offset
	 */
	@TableField(exist=false)
	private Integer offset;

	/**
	 * 设置查询的偏移量
	 */
	public void setOffset() {
		if(current <= 0)
			current = 1;
		
		if(limit <= 0)
			limit = 10;
		
		offset = (current - 1) * limit;
		if(offset < 0)
			offset = 0;
	}
	
	/**
	 * 设置分页条件
	 * @param pageModel
	 * @param query
	 */
	public void assembling(PageQuery query) {
		if(query != null) {
			assembling(query.getSize(), query.getCurrent());
		}
	}
	
	/**
	 * 设置分页
	 * @param pageModel
	 * @param rows
	 * @param page
	 */
	public void assembling(Integer rows, Integer page) {
		if(rows != null && page != null) {
			this.setLimit(rows);
			this.setCurrent(page);
			this.setOffset();
		}
	}
	
	/**
	 * 审核人条件查询时设置分页
	 * @param pages 分页对象
	 * @param list 数据集合
	 * @param count 总记录数
	 */
	public <T> void setPages(IPage<T> pages, List<T> list, Integer count) {
		if(pages != null) {
			pages.setRecords(list);
			pages.setTotal(count);
			pages.setSize(this.getLimit());
			pages.setCurrent(this.getCurrent());
		}
	}
	
	/**
	 * 增加key值
	 * @param models
	 */
	public void addModelKey(List<? extends SystemModel> models) {
		for (SystemModel systemModel : models) {
			if(systemModel != null) {
				systemModel.setKey(systemModel.getId());
			}
		}
	}
}
