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
package org.yellowsneakers.boot.runner;

/**
 * 
 * @author tang
 *
 */
public interface AppConstants {
    /**
     * 应用版本
     */
    String APPLICATION_VERSION = "1.0.0";

    /**
     * consul
     */
    //consul地址
    String CONSUL_HOST = "http://localhost";

    //consul端口
    String CONSUL_PORT = "8500";

    //
    String CONSUL_CONFIG_FORMAT = "yaml";

    //consul
    String CONSUL_WATCH_DELAY = "1000";

    //
    String CONSUL_WATCH_ENABLED = "true";

    /**
     * 基础包
     */
    String BASE_PACKAGES = "org.yellowsneakers";

    /**
     * zookeeper
     */
    //zookeeper id
    String ZOOKEEPER_ID = "zk";

    //zookeeper connect string
    String ZOOKEEPER_CONNECT_STRING = "127.0.0.1:2181";

    //zookeeper address
    String ZOOKEEPER_ADDRESS = "zookeeper://" + ZOOKEEPER_CONNECT_STRING;

    //zookeeper root
    String ZOOKEEPER_ROOT = "/blade-services";

    /**
     * 应用名
     */
    String APPLICATION_NAME = "yellowsneakers";

    /**
     * 开发环境
     */
    String DEV_CDOE = "dev";
    /**
     * 生产环境
     */
    String PROD_CODE = "prod";
    /**
     * 测试环境
     */
    String TEST_CODE = "test";

    /**
     * 代码部署于 Linux 上
     */
    String OS_NAME_LINUX = "LINUX";
}
