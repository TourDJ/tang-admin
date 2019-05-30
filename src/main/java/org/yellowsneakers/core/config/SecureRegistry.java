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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Data;

/**
 * 
 * @author tang
 * @since  1.0 
 */
@Data
public class SecureRegistry {

    private final List<String> defaultExcludePatterns = new ArrayList<>();

    private final List<String> excludePatterns = new ArrayList<>();

    public SecureRegistry() {
        this.defaultExcludePatterns.add("/doc.html");
        this.defaultExcludePatterns.add("/js/**");
        this.defaultExcludePatterns.add("/webjars/**");
        this.defaultExcludePatterns.add("/swagger-resources/**");
        this.defaultExcludePatterns.add("/auth/**");
        this.defaultExcludePatterns.add("/log/**");
    }

    /**
     * 设置放行
     * @param patterns
     * @return
     */
    public SecureRegistry excludePathPatterns(String... patterns) {
        return excludePathPatterns(Arrays.asList(patterns));
    }

    /**
     * 设置放行
     * @param patterns
     * @return
     */
    public SecureRegistry excludePathPatterns(List<String> patterns) {
        this.excludePatterns.addAll(patterns);
        return this;
    }

}
