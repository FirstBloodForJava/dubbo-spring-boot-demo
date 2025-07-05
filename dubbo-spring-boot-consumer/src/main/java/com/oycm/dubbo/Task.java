package com.oycm.dubbo;

import com.oycm.dubbo.api.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 消费者在应用启动后, 启动线程调用服务提供者
 */
@Component
public class Task implements CommandLineRunner {
    @DubboReference
    private DemoService demoService;

    @Override
    public void run(String... args) {
        String result = demoService.sayHello("World !");
        System.out.println("Receive result ======> " + result);
        AtomicInteger count = new AtomicInteger();
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(new Date() + " Receive result ======> " + demoService.sayHello("World ") + count.getAndIncrement() + " !");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }
}
