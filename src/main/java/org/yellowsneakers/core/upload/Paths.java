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
package org.yellowsneakers.core.upload;

import java.io.File;
import java.net.URL;

import org.springframework.lang.Nullable;
import org.yellowsneakers.core.tools.Charsets;
import org.yellowsneakers.generic.utils.URLUtils;

/**
 * 
 * @author tang
 * @since  1.0 
 */
public class Paths {
	public static final String FILE_PROTOCOL = "file";
	public static final String JAR_PROTOCOL = "jar";
	public static final String ZIP_PROTOCOL = "zip";
	public static final String FILE_PROTOCOL_PREFIX = "file:";
	public static final String JAR_FILE_SEPARATOR = "!/";

	/**
	 * 获取jar包运行时的当前目录
	 * @return {String}
	 */
	@Nullable
	public static String getJarPath() {
		try {
			URL url = Paths.class.getResource("/").toURI().toURL();
			return Paths.toFilePath(url);
		} catch (Exception e) {
			String path = Paths.class.getResource("").getPath();
			return new File(path).getParentFile().getParentFile().getAbsolutePath();
		}
	}

	@Nullable
	public static String toFilePath(@Nullable URL url) {
		if (url == null) { return null; }
		String protocol = url.getProtocol();
		String file = URLUtils.decodeURL(url.getPath(), Charsets.UTF_8);
		if (FILE_PROTOCOL.equals(protocol)) {
			return new File(file).getParentFile().getParentFile().getAbsolutePath();
		} else if (JAR_PROTOCOL.equals(protocol) || ZIP_PROTOCOL.equals(protocol)) {
			int ipos = file.indexOf(JAR_FILE_SEPARATOR);
			if (ipos > 0) {
				file = file.substring(0, ipos);
			}
			if (file.startsWith(FILE_PROTOCOL_PREFIX)) {
				file = file.substring(FILE_PROTOCOL_PREFIX.length());
			}
			return new File(file).getParentFile().getAbsolutePath();
		}
		return file;
	}

}
