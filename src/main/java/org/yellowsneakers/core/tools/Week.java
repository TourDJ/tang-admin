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
package org.yellowsneakers.core.tools;

import java.util.Calendar;

/**
 * 
 * @author tang
 * @since  1.0
 */
public enum Week {

	/** 周日 */
	SUNDAY(Calendar.SUNDAY),

	/** 周一 */
	MONDAY(Calendar.MONDAY),

	/** 周二 */
	TUESDAY(Calendar.TUESDAY),

	/** 周三 */
	WEDNESDAY(Calendar.WEDNESDAY),

	/** 周四 */
	THURSDAY(Calendar.THURSDAY),

	/** 周五 */
	FRIDAY(Calendar.FRIDAY),

	/** 周六 */
	SATURDAY(Calendar.SATURDAY);

	/**
	 * 星期对应{@link Calendar} 中的Week值
	 */
	private int value;

	/**
	 * 构造
	 * 
	 * @param value 星期对应{@link Calendar} 中的Week值
	 */
	private Week(int value) {
		this.value = value;
	}

	/**
	 * 获得星期对应{@link Calendar} 中的Week值
	 * 
	 * @return 星期对应{@link Calendar} 中的Week值
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * 转换为中文名
	 * 
	 * @return 星期的中文名
	 * @since 3.3.0
	 */
	public String toChinese() {
		switch (this) {
		case SUNDAY:
			return "星期日";
		case MONDAY:
			return "星期一";
		case TUESDAY:
			return "星期二";
		case WEDNESDAY:
			return "星期三";
		case THURSDAY:
			return "星期四";
		case FRIDAY:
			return "星期五";
		case SATURDAY:
			return "星期六";
		default:
			return null;
		}
	}

	/**
	 * 将 {@link Calendar}星期相关值转换为Week枚举对象<br>
	 * 
	 * @see #SUNDAY
	 * @see #MONDAY
	 * @see #TUESDAY
	 * @see #WEDNESDAY
	 * @see #THURSDAY
	 * @see #FRIDAY
	 * @see #SATURDAY
	 * 
	 * @param calendarWeekIntValue Calendar中关于Week的int值
	 * @return {@link Week}
	 */
	public static Week of(int calendarWeekIntValue) {
		switch (calendarWeekIntValue) {
		case Calendar.SUNDAY:
			return SUNDAY;
		case Calendar.MONDAY:
			return MONDAY;
		case Calendar.TUESDAY:
			return TUESDAY;
		case Calendar.WEDNESDAY:
			return WEDNESDAY;
		case Calendar.THURSDAY:
			return THURSDAY;
		case Calendar.FRIDAY:
			return FRIDAY;
		case Calendar.SATURDAY:
			return SATURDAY;
		default:
			return null;
		}
	}
}
