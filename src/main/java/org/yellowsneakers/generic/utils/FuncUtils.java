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

import java.util.Objects;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * Function utility class.
 * @author tang
 *
 */
public class FuncUtils {
	
	private FuncUtils() {
		
	}

	/**
	 *
	 * @return
	 */
	public static String getRoles() {
		
		return "";
	}

	/**
	 * 检测用户是否包含指定权限
	 *
	 * @param ro
	 * @return
	 */
	public static boolean containRole(Integer ro) {
		Objects.requireNonNull(ro);
		
		boolean flag = false;
		String role = getRoles();
		if (CommonUtils.notEmpty(role)) {
			String[] roles = role.split(",");
			if (roles != null && roles.length > 0) {
				for (int i = 0; i < roles.length; i++) {
					if (ro == CommonUtils.parseInt(roles[i])) {
						flag = true;
						break;
					}
				}
			}
		}
		return flag;
	}

	/**
	 * 验证是否是管理员
	 *
	 * @return
	 */
	public static boolean isAdmin() {
		boolean flag = false;
		String role = getRoles();
		
		if (CommonUtils.notEmpty(role)) {
			String[] roles = role.split(",");
			for (int i = 0, len = roles.length; i < len; i++) {
				if (CommonUtils.parseInt(roles[i]) == CommonConstants.SYSTEM_LOGIN_ADMIN) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 判断是否是管理员进行相应设置
	 * @param userId 用户id
	 * @param qw
	 * @param role
	 * @param qw
	 */
	public static <T> void checkUserAuth(Integer userId, QueryWrapper<T> qw, Integer role) {
		if (!isAdmin()) {
			// 特定权限
			if (role == null || !containRole(role)) {
				WrapperProcess.ensureStatus(qw, userId);
				return;
			}
		}
		WrapperProcess.ensureStatus(qw, null);
	}
}
