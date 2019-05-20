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
package org.yellowsneakers.generic.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.Data;

/**
 * A page query class which controls the page relative arguments.
 * @author tang
 *
 */
@Data
public class PageQuery implements Serializable {

	private static final long serialVersionUID = 3863967310591896916L;

	/**
     * 当前页
     */
    private Integer current = 1;
    
    /**
     * 每页的数量
     */
    private Integer size = 10;
    
    /**
     * 排序字段
     */
    private String order;
    
    /**
     * 是否倒序排序
     */
    private boolean desc;
    
    public <T> Page<T> toPage() {
        return new Page<>(current, size);
    }
    
	public <T> Page<T> paging(List<T> datas) {
		
		List<T> currentPageData = new ArrayList<>();
		
		datas.stream().skip((this.current - 1 ) * this.size).limit(this.size).forEach(e -> currentPageData.add(e));
		
		Page<T> pages = this.toPage();
		pages.setRecords(currentPageData);
		pages.setTotal(datas.size());
		pages.setSize(this.size);
		pages.setCurrent(this.current);
		
		return pages;
	}

}
