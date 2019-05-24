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

import org.yellowsneakers.generic.utils.DateUtils;

/**
 * 计时器
 * @author tang
 * @since  1.0 
 */
public class TimeInterval {
	private long time;
	private boolean isNano;

	public TimeInterval() {
		this(false);
	}

	public TimeInterval(boolean isNano) {
		this.isNano = isNano;
		start();
	}

	/**
	 * @return 开始计时并返回当前时间
	 */
	public long start() {
		time = DateUtils.current(isNano);
		return time;
	}

	/**
	 * @return 重新计时并返回从开始到当前的持续时间
	 */
	public long intervalRestart() {
		long now = DateUtils.current(isNano);
		long d = now - time;
		time = now;
		return d;
	}
	
	/**
	 * 重新开始计算时间（重置开始时间）
	 * @return this
	 * @since 3.0.1
	 */
	public TimeInterval restart(){
		time = DateUtils.current(isNano);
		return this;
	}

	//----------------------------------------------------------- Interval
	/**
	 * 从开始到当前的间隔时间（毫秒数）<br>
	 * 如果使用纳秒计时，返回纳秒差，否则返回毫秒差
	 * @return 从开始到当前的间隔时间（毫秒数）
	 */
	public long interval() {
		return DateUtils.current(isNano) - time;
	}
	
	/**
	 * 从开始到当前的间隔时间（毫秒数）
	 * @return 从开始到当前的间隔时间（毫秒数）
	 */
	public long intervalMs() {
		return isNano ? interval() / 1000000L : interval();
	}
	
	/**
	 * 从开始到当前的间隔秒数，取绝对值
	 * @return 从开始到当前的间隔秒数，取绝对值
	 */
	public long intervalSecond(){
		return intervalMs() / DateUnit.SECOND.getMillis();
	}
	
	/**
	 * 从开始到当前的间隔分钟数，取绝对值
	 * @return 从开始到当前的间隔分钟数，取绝对值
	 */
	public long intervalMinute(){
		return intervalMs() / DateUnit.MINUTE.getMillis();
	}
	
	/**
	 * 从开始到当前的间隔小时数，取绝对值
	 * @return 从开始到当前的间隔小时数，取绝对值
	 */
	public long intervalHour(){
		return intervalMs() / DateUnit.HOUR.getMillis();
	}
	
	/**
	 * 从开始到当前的间隔天数，取绝对值
	 * @return 从开始到当前的间隔天数，取绝对值
	 */
	public long intervalDay(){
		return intervalMs() / DateUnit.DAY.getMillis();
	}
	
	/**
	 * 从开始到当前的间隔周数，取绝对值
	 * @return 从开始到当前的间隔周数，取绝对值
	 */
	public long intervalWeek(){
		return intervalMs() / DateUnit.WEEK.getMillis();
	}
	
}
