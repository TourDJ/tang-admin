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
package org.yellowsneakers.generic.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.yellowsneakers.generic.query.PageQuery;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * MyBatis plus wrapper operate class.
 * @author tang
 *
 */
public class WrapperProcess {

	private WrapperProcess() {
		
	}
	
	/**
	 * 设置有效状态
	 * @param qw 实体包装对象
	 */
	public static <T> void ensureStatus(QueryWrapper<T> qw) {
		qw.eq("status", 1);
	}
	
	/**
	 * 设置有效状态
	 * @param qw 实体包装对象
	 * @param userId 用户id
	 */
	public static <T> void ensureStatus(QueryWrapper<T> qw, Integer userId) {
		ensureStatus(qw);
		if(userId != null)
			qw.eq("create_user", userId);
	}
	
//	/**
//	 * 设置有效状态
//	 * @param qw 实体包装对象
//	 * @param userId 用户id
//	 * @param flow 是否为流程对象。true：是，false：不是。
//	 */
//	public static <T> void ensureStatus(QueryWrapper<T> qw, Integer userId, boolean flow) {
//		ensureStatus(qw, userId);
//		if(!flow)
//			ensureStatus(qw);
//	}

	/**
	 * 设置排序属性
	 * 
	 * @param query
	 */
	public static void sortRecords(PageQuery query) {
		if (query != null) {
			query.setOrder(CommonConstants.DEFAULT_SORT_FIELD);
		}
	}
	
	/**
	 * 设置排序属性
	 * @param qw 实体对象
	 * @param desc 是否是倒序
	 * @param args 参数
	 */
	public static <T> void sortRecords(QueryWrapper<T> qw, boolean desc, String... args) {
		Collection<String> collection = new ArrayList<>();
		if(args != null) {
			for (String string : args) {
				if(string != null)
					collection.add(string);
			}
		}
		
		if(collection.size() == 0)
			collection.add(CommonConstants.DEFAULT_SORT_FIELD);
		
		String[] orders = new String[collection.size()];
		orders = collection.toArray(orders);
		if(qw != null) {
			if(desc)
				qw.orderByDesc(orders);
			else
				qw.orderByAsc(orders);
		}
	}
	
	/**
	 * 增加单条查询条件
	 * @param qw
	 * @param field
	 * @param value
	 * @param equal
	 */
	public static <T> void addQueryArgs(QueryWrapper<T> qw, String field, Object value, boolean equal) {
		if(value != null) {
			if(equal) {
				qw.eq(field, value);
			} else
				qw.like(field, String.valueOf(value));
		}
	}
	
	/**
	 * 增加多条查询条件
	 * @param qw
	 * @param args
	 * @param equal
	 */
	public static <T> void addQueryArgs(QueryWrapper<T> qw, Map<String, Object> args, boolean equal) {
		if(args != null) {
			args.forEach((k, v) -> {
				if(v instanceof Collection) {
					Collection<?> collection = (Collection<?>) v;
					qw.in(k, collection);
				} else
					addQueryArgs(qw, k, v, equal);
			});
		}
	}

}
