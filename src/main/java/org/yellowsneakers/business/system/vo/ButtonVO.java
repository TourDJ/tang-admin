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
 * 按钮
 * @author tang
 *
 */
@Data
public class ButtonVO implements Serializable {

	private static final long serialVersionUID = -2053918931389933109L;
	
	/**
	 * 请求地址
	 */
	private String path;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 别名
	 */
	private String alias;
	
	private Integer sort;
}
