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
package org.yellowsneakers.generic.query;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * 
 * @author tang
 *
 * @param <T>
 */
@Data
@ToString
@AllArgsConstructor
public class DataWrapper<T> implements Serializable {

	private static final long serialVersionUID = -4911762250330643779L;
	
	private int status;
	
	private String message;
	
	private T data;
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public static <T> DataWrapper<T> data(T data) {
		return new DataWrapper<T>(200, "success", data);
	}
	
	/**
	 * 
	 * @param bool
	 * @return
	 */
	public static DataWrapper<Boolean> status(Boolean bool){
		return data(bool);
	}
}
