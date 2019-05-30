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
package org.yellowsneakers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.yellowsneakers.core.runner.AppConstants;
import org.yellowsneakers.generic.utils.StringUtils;

/**
 * 
 * @author tang
 * @since 1.0
 */
public class RubberSpringApplication {

	private static String profile;

	private static String[] activeProfiles;

	private static List<String> activeProfileList;

	private static ConfigurableEnvironment environment = new StandardEnvironment();

	public static ConfigurableApplicationContext run(Class<?> clazz, String... args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(clazz);

		// 使用 Spring boot 的规则读取环境变量
		addPropertySources(args);

		filterActiveProfiles();

		parseProfile();

		loadProperties();

		return builder.profiles(profile).run(args);
	}

	private static void loadProperties() {
		System.setProperty("rubber.env", profile);
	}

	private static void filterActiveProfiles() {

		// 获取配置的环境变量
		activeProfiles = environment.getActiveProfiles();

		// 判断环境:dev、test、prod
		List<String> profiles = Arrays.asList(activeProfiles);
		// 预设的环境
		List<String> presetProfiles = new ArrayList<>(
				Arrays.asList(AppConstants.DEV_CDOE, AppConstants.TEST_CODE, AppConstants.PROD_CODE));
		// 交集
		presetProfiles.retainAll(profiles);

		// 当前使用
		activeProfileList = new ArrayList<>(profiles);
	}

	private static void addPropertySources(String... args) {
		MutablePropertySources propertySources = environment.getPropertySources();
		// 读取命令行参数
		propertySources.addFirst(new SimpleCommandLinePropertySource(args));
		propertySources.addLast(new MapPropertySource(StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME,
				environment.getSystemProperties()));
		propertySources.addLast(new SystemEnvironmentPropertySource(
				StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME, environment.getSystemEnvironment()));
	}

	private static void parseProfile() {
		if (activeProfileList.isEmpty()) {
			profile = AppConstants.DEV_CDOE;
			activeProfileList.add(profile);
		} else if (activeProfileList.size() == 1) {
			profile = activeProfileList.get(0);
		} else {
			throw new RuntimeException("同时存在环境变量:[" + StringUtils.arrayToCommaDelimitedString(activeProfiles) + "]");
		}
	}
}
