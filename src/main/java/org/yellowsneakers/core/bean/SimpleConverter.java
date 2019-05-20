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
package org.yellowsneakers.core.bean;

import java.lang.reflect.Field;

import org.springframework.cglib.core.Converter;
import org.springframework.core.convert.TypeDescriptor;
import org.yellowsneakers.generic.utils.StringUtils;

/**
 * A simple implementation of spring's converter.
 * 
 * @author tang
 *
 */
public class SimpleConverter implements Converter {

	private final Class<?> srcClazz;

	private final Class<?> targetClazz;

	public SimpleConverter(Class<?> srcClazz, Class<?> targetClazz) {
		this.srcClazz = srcClazz;
		this.targetClazz = targetClazz;
	}

	/**
	 * cglib convert
	 * 
	 * @param value
	 *            源对象属性
	 * @param target
	 *            目标对象属性类
	 * @param context
	 *            目标对象setter方法名
	 * @return {Object}
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object convert(Object value, Class target, Object context) {
		if (value.getClass() == target) {
			return value;
		}
		// 获取
		String fieldName = StringUtils.lowerFirst(((String) context).substring(3));
		try {
			Field srcField = srcClazz.getDeclaredField(fieldName);
			Field targetField = targetClazz.getDeclaredField(fieldName);
			return Converts.convert(value, new TypeDescriptor(srcField), new TypeDescriptor(targetField));
		} catch (NoSuchFieldException e) {
			return null;
		}
	}
}
