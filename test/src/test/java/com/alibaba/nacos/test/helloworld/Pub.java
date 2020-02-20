package com.alibaba.nacos.test.helloworld;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;

/**
 * @author zhixiang.yuan
 * @since 2020/02/20 12:38:51
 */
public class Pub {

    public static void main(String[] args) throws NacosException, InterruptedException {
        // 发布的服务名
        String serviceName = "helloworld.services";
        // 构造一个 nacos 实例，入参是 nacos server 的 ip 和服务端口
        NamingService naming = NamingFactory.createNamingService("127.0.0.1:8848");
        // 发布一个服务，该服务对外提供的 ip 为 127.0.0.1，端口为 8888
        naming.registerInstance(serviceName, "127.0.0.1", 8888);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
