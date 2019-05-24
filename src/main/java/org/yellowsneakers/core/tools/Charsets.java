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
package org.yellowsneakers.core.tools;

import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;

import org.yellowsneakers.generic.utils.StringUtils;

/**
 * 
 * @author tang
 *
 */
public class Charsets {
	
	/**
	 * 字符集ISO-8859-1
	 */
	public static final java.nio.charset.Charset ISO_8859_1 = StandardCharsets.ISO_8859_1;
	
	/**
	 * 字符集GBK
	 */
	public static final java.nio.charset.Charset GBK = java.nio.charset.Charset.forName("GBK");
	
	/**
	 * 字符集utf-8
 	 */
	public static final java.nio.charset.Charset UTF_8 = StandardCharsets.UTF_8;

    /**
     * 转换为Charset对象
     * @param charsetName 字符集，为空则返回默认字符集
     * @return Charset
     * @throws UnsupportedCharsetException 编码不支持
     */
    public static java.nio.charset.Charset charset(String charsetName) throws UnsupportedCharsetException {
        return StringUtils.isBlank(charsetName) ? java.nio.charset.Charset.defaultCharset() : java.nio.charset.Charset.forName(charsetName);
    }
    
}
