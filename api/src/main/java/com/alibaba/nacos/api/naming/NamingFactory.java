/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
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
package com.alibaba.nacos.api.naming;

import java.lang.reflect.Constructor;
import java.util.Properties;

import com.alibaba.nacos.api.exception.NacosException;

/**
 * Naming Factory
 *
 * @author nkorange
 */
public class NamingFactory {

    public static NamingService createNamingService(String serverList) throws NacosException {
        try {
            // 这个地方很精彩，这不就是我一直在思考的插件加载模式么
            // 实际的插件主类通过接口的形式定义出来，然后通过 Class.forName 触发类加载
            // 在获取到该类后通过构造函数 new 出来，这样就实现了可插拔的方式
            Class<?> driverImplClass = Class.forName("com.alibaba.nacos.client.naming.NacosNamingService");
            Constructor constructor = driverImplClass.getConstructor(String.class);
            NamingService vendorImpl = (NamingService)constructor.newInstance(serverList);
            return vendorImpl;
        } catch (Throwable e) {
            throw new NacosException(NacosException.CLIENT_INVALID_PARAM, e);
        }
    }

    public static NamingService createNamingService(Properties properties) throws NacosException {
        try {
            Class<?> driverImplClass = Class.forName("com.alibaba.nacos.client.naming.NacosNamingService");
            Constructor constructor = driverImplClass.getConstructor(Properties.class);
            NamingService vendorImpl = (NamingService)constructor.newInstance(properties);
            return vendorImpl;
        } catch (Throwable e) {
            throw new NacosException(NacosException.CLIENT_INVALID_PARAM, e);
        }
    }
}
