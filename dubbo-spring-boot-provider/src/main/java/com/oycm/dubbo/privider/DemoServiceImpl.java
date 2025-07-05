package com.oycm.dubbo.privider;

import com.oycm.dubbo.api.DemoService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        System.out.println(name);
        return "Hello " + name;
    }
}
