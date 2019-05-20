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
package org.yellowsneakers.business.system.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 部门
 * @author tang
 *
 */
@Data
public class DeptVO implements Serializable {

	private static final long serialVersionUID = 4012753016753083336L;

	/**
	 * 部门id
	 */
	private Integer id;
	
	/**
	 * 部门名称
	 */
	private String name;
}
