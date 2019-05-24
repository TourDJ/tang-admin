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
package org.yellowsneakers.boot.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.yellowsneakers.core.env.SystemEnv;
import org.yellowsneakers.core.runner.RubberProperties;
import org.yellowsneakers.core.secure.AuthAspect;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author tang
 * @since  1.0
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({
        RubberProperties.class
})
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@AllArgsConstructor
public class BootAutoConfiguration {

    private RubberProperties properties;

    /**
     * 权限认证
     */
    @Bean
    public AuthAspect authAspect() {
        return new AuthAspect();
    }

    /**
     * 全局变量定义
     */
    @Bean
    public SystemEnv fileConst() {
    	SystemEnv me = SystemEnv.me();
    	log.debug("system env");

        //设定开发模式
        me.setDevMode((properties.getEnv().equals("dev") ? true : false));

        //设定文件上传远程地址
        me.setDomain(properties.get("upload-domain", "http://localhost:8888"));

        //设定文件上传是否为远程模式
        me.setRemoteMode(properties.getBoolean("remote-mode", true));

        //远程上传地址
        me.setRemotePath(properties.get("remote-path", System.getProperty("user.dir") + "/work/rubber"));

        //设定文件上传头文件夹
        me.setUploadPath(properties.get("upload-path", "/upload"));

        //设定文件下载头文件夹
        me.setDownloadPath(properties.get("download-path", "/download"));

        //设定上传图片是否压缩
        me.setCompress(properties.getBoolean("compress", false));

        //设定上传图片压缩比例
        me.setCompressScale(properties.getDouble("compress-scale", 2.00));

        //设定上传图片缩放选择:true放大;false缩小
        me.setCompressFlag(properties.getBoolean("compress-flag", false));

        return me;
    }

}
