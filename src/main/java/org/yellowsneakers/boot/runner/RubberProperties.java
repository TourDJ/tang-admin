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
package org.yellowsneakers.boot.runner;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * 配置文件
 * @author tang
 * @since  1.0 
 */
@ConfigurationProperties("rubber")
public class RubberProperties {

	/**
	 * 开发环境
	 */
	@Getter
	@Setter
	private String env;

    /**
     * 服务名
     */
    @Getter
    @Setter
    private String name;

    /**
     * 判断是否为 本地开发环境
     */
    @Getter
    @Setter
    private Boolean isLocal = Boolean.FALSE;

	/**
	 * 装载自定义配置blade.prop.xxx
	 */
	@Getter
	private final Map<String, String> prop = new HashMap<>();

	/**
	 * 获取配置
	 *
	 * @param key key
	 * @return value
	 */
	public String get(String key) {
		return get(key, null);
	}

	/**
	 * 获取配置
	 *
	 * @param key          key
	 * @param defaultValue 默认值
	 * @return value
	 */
	public String get(String key, String defaultValue) {
		String value = prop.get(key);
		if (value == null) {
			return defaultValue;
		}
		return value;
	}

	/**
	 * 获取配置
	 *
	 * @param key key
	 * @return int value
	 */
	public Integer getInt(String key) {
		return getInt(key, null);
	}

	/**
	 * 获取配置
	 *
	 * @param key          key
	 * @param defaultValue 默认值
	 * @return int value
	 */
	public Integer getInt(String key, Integer defaultValue) {
		String value = prop.get(key);
		if (value != null) {
			return Integer.valueOf(value.trim());
		}
		return defaultValue;
	}

	/**
	 * 获取配置
	 *
	 * @param key key
	 * @return long value
	 */
	public Long getLong(String key) {
		return getLong(key, null);
	}

	/**
	 * 获取配置
	 *
	 * @param key          key
	 * @param defaultValue 默认值
	 * @return long value
	 */
	public Long getLong(String key, Long defaultValue) {
		String value = prop.get(key);
		if (value != null) {
			return Long.valueOf(value.trim());
		}
		return defaultValue;
	}

	/**
	 * 获取配置
	 *
	 * @param key key
	 * @return Boolean value
	 */
	public Boolean getBoolean(String key) {
		return getBoolean(key, null);
	}

	/**
	 * 获取配置
	 *
	 * @param key          key
	 * @param defaultValue 默认值
	 * @return Boolean value
	 */
	public Boolean getBoolean(String key, Boolean defaultValue) {
		String value = prop.get(key);
		if (value != null) {
			value = value.toLowerCase().trim();
			if ("true".equals(value)) {
				return Boolean.TRUE;
			} else if ("false".equals(value)) {
				return Boolean.FALSE;
			}
			throw new RuntimeException("The value can not parse to Boolean : " + value);
		}
		return defaultValue;
	}

    /**
     * 获取配置
     *
     * @param key key
     * @return double value
     */
    public Double getDouble(String key) {
        return getDouble(key, null);
    }

    /**
     * 获取配置
     *
     * @param key          key
     * @param defaultValue 默认值
     * @return double value
     */
    public Double getDouble(String key, Double defaultValue) {
        String value = prop.get(key);
        if (value != null) {
            return Double.parseDouble(value.trim());
        }
        return defaultValue;
    }

	/**
	 * 判断是否存在key
	 *
	 * @param key prop key
	 * @return boolean
	 */
	public boolean containsKey(String key) {
		return prop.containsKey(key);
	}

}
