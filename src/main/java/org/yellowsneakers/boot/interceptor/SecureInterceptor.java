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
package org.yellowsneakers.boot.interceptor;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;
import org.yellowsneakers.core.http.HttpHelper;
import org.yellowsneakers.core.http.StatusInfo;
import org.yellowsneakers.core.mybatisplus.QueryData;
import org.yellowsneakers.core.secure.TokenSecurer;
import org.yellowsneakers.generic.utils.JsonUtils;
import org.yellowsneakers.generic.utils.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author tang
 * @since  1.0 
 */
@Slf4j
public class SecureInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(TokenSecurer.getUser().getUserId() != null) {
			return true;
		} else {
			log.warn("签名认证失败，请求接口：{}，请求IP：{}，请求参数：{}", request.getRequestURI(), HttpHelper.getIP(request), JsonUtils.toJson(request.getParameterMap()));
			QueryData<?> result = QueryData.failure(StatusInfo.UN_AUTHORIZED);
            response.setCharacterEncoding(StringUtils.UTF_8);
            response.setHeader("Content-type", MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_OK);
            try {
                response.getWriter().write(Objects.requireNonNull(JsonUtils.toJson(result)));
            } catch (IOException ex) {
                log.error(ex.getMessage());
            }
			return false;
		}
	}
}
