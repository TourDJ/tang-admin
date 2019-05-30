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
package org.yellowsneakers.core.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.yellowsneakers.boot.filter.RubberCorsFilter;
import org.yellowsneakers.boot.interceptor.SecureInterceptor;

import lombok.AllArgsConstructor;

/**
 * 
 * @author tang
 * @since 1.0
 */
@Order
@Configuration
@AllArgsConstructor
public class SecureConfigure implements WebMvcConfigurer {

	private final SecureRegistry secureRegistry;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SecureInterceptor())
				.excludePathPatterns(secureRegistry.getExcludePatterns())
				.excludePathPatterns(secureRegistry.getDefaultExcludePatterns());
	}
	
	/**
     * 跨域过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean<RubberCorsFilter> corsFilter() {
	      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	      CorsConfiguration config = new CorsConfiguration();
	      config.setAllowCredentials(true);
	      config.addAllowedOrigin("*");
	      config.addAllowedHeader("*");
	      config.addAllowedMethod("*");
	      source.registerCorsConfiguration("/**", config);
	      FilterRegistrationBean<RubberCorsFilter> bean = new FilterRegistrationBean<>(new RubberCorsFilter(source));
	      bean.setOrder(0);
	      return bean;
    }
    
}
