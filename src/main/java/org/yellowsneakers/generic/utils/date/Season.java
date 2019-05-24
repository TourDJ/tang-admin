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
package org.yellowsneakers.generic.utils.date;

/**
 * 
 * @author tang
 * @since  1.0 
 */
public enum Season {

	/** 春季（第一季度） */
	SPRING(1),
	/** 夏季（第二季度） */
	SUMMER(2),
	/** 秋季（第三季度） */
	AUTUMN(3),
	/** 冬季（第四季度） */
	WINTER(4);
	
	// ---------------------------------------------------------------
	private int value;

	private Season(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	/**
	 * 将 季度int转换为Season枚举对象<br>
	 * 
	 * @see #SPRING
	 * @see #SUMMER
	 * @see #AUTUMN
	 * @see #WINTER
	 * 
	 * @param intValue 季度int表示
	 * @return {@link Season}
	 */
	public static Season of(int intValue) {
		switch (intValue) {
			case 1:
				return SPRING;
			case 2:
				return SUMMER;
			case 3:
				return AUTUMN;
			case 4:
				return WINTER;
			default:
				return null;
		}
	}
}
