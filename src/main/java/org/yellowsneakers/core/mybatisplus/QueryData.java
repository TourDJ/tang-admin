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
package org.yellowsneakers.core.mybatisplus;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import org.yellowsneakers.core.http.StatusInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * 
 * @author tang
 * @since 1.0
 */
@Data
@ToString
@AllArgsConstructor
public class QueryData<T> implements Serializable, QueryConstants {

	private static final long serialVersionUID = 320654703588570716L;

	private int status = HttpServletResponse.SC_OK;

	private T data;

	private String message = DEFAULT_SUCCESS_MESSAGE;

	private QueryData(StatusInfo status) {
		this(status, null, status.getMessage());
	}

	private QueryData(StatusInfo status, String message) {
		this(status, null, message);
	}

	private QueryData(StatusInfo status, T data, String message) {
		this.status = status.getStatusCode();
		this.data = data;
		this.message = message;
	}

	/**
	 * 返回R
	 * 
	 * @param data 数据
	 */
	public static <T> QueryData<T> data(T data) {
		return data(data, DEFAULT_SUCCESS_MESSAGE);
	}

	/**
	 * 返回R
	 * 
	 * @param data 数据
	 * @param message 消息
	 */
	public static <T> QueryData<T> data(T data, String message) {
		return data(HttpServletResponse.SC_OK, data, message);
	}

	/**
	 * 返回R
	 * 
	 * @param code 状态码
	 * @param data 数据
	 * @param message 消息
	 */
	public static <T> QueryData<T> data(int code, T data, String message) {
		return new QueryData<>(code, data, data == null ? DEFAULT_NULL_MESSAGE : message);
	}

	/**
	 * 返回R
	 * 
	 * @param message 消息
	 */
	public static <T> QueryData<T> success(String message) {
		return new QueryData<>(StatusInfo.SUCCESS, message);
	}

	/**
	 * 返回R
	 * 
	 * @param status 业务代码
	 */
	public static <T> QueryData<T> success(StatusInfo status) {
		return new QueryData<>(status);
	}

	/**
	 * 返回R
	 * 
	 * @param status 业务代码
	 */
	public static <T> QueryData<T> success(StatusInfo status, String message) {
		return new QueryData<>(status, message);
	}

	/**
	 * 返回R
	 * 
	 * @param message 消息
	 */
	public static <T> QueryData<T> failure(String message) {
		return new QueryData<>(StatusInfo.FAILURE, message);
	}

	/**
	 * 返回R
	 * 
	 * @param status 业务代码
	 */
	public static <T> QueryData<T> failure(StatusInfo status) {
		return new QueryData<>(status);
	}

	/**
	 * 返回R
	 * 
	 * @param status 业务代码
	 */
	public static <T> QueryData<T> failure(StatusInfo status, String message) {
		return new QueryData<>(status, message);
	}

	/**
	 * 返回R
	 * 
	 * @param flag 成功状态
	 */
	public static <T> QueryData<T> status(boolean flag) {
		return flag ? success(DEFAULT_SUCCESS_MESSAGE) : failure(DEFAULT_FAILURE_MESSAGE);
	}

}
