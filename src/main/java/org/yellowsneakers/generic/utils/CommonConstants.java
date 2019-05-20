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

/**
 * Common constants interface used for all classes.
 * @author tang
 *
 */
public interface CommonConstants {

	/**
	 * 用户权限
	 */
	int SYSTEM_LOGIN_ADMIN = 1;  //管理员
	
	String DEFAULT_SORT_FIELD = "update_time";
	
	int DEFAULT_DEPT_ROOT_CODE = 0;
	
	String DEFAULT_DEPT_ROOT_NAME = "root";
	
	int BUSY_ORDER_STATUS_DRAFT = 0;
}
